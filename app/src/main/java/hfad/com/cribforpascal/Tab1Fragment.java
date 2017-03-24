package hfad.com.cribforpascal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hfad.com.cribforpascal.data.CribForPascalContract;
import hfad.com.cribforpascal.data.CribForPascalDBHelper;

/**
 * Created by Mephisto on 3/22/2017.
 */
public class Tab1Fragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private Cursor mDescrioptionCursor;
    private SQLiteDatabase mDatabase;
    public static final int DESCRIPTION_LOADER = 1;
    private Cursor mCursor;
    private TextView tvDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CribForPascalDBHelper dbHelper = new CribForPascalDBHelper(getContext());
        mDatabase = dbHelper.getReadableDatabase();
        getActivity().getSupportLoaderManager().initLoader(DESCRIPTION_LOADER, null, this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        tvDescription = (TextView)getActivity().findViewById(R.id.tv_description_fragment);
        if (mDescrioptionCursor.moveToFirst())
        {
            do
            {
                String statementDescription = mDescrioptionCursor.getString(0);
                Log.e("TAG", "ERROR HERE"+statementDescription);
                tvDescription.setText(statementDescription);
            } while (mDescrioptionCursor.moveToNext());
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Intent descriptionIntent = getActivity().getIntent();
        final String descriptionForStatement = descriptionIntent.getStringExtra("statementText");
        final String selection = CribForPascalContract.CribForPascalEntry.COLUMN_DESCRIPTION + "=?";

        return new AsyncTaskLoader<Cursor>(getContext()) {
            @Override
            protected void onStartLoading() {
                if (mDescrioptionCursor != null) {
                    deliverResult(mDescrioptionCursor);
                } else forceLoad();
            }

            @Override
            public Cursor loadInBackground() {
                try {
                  return mDescrioptionCursor = mDatabase.rawQuery("select description from crib where statement = ?", new String[] { descriptionForStatement });
                } catch (Exception e) {
                    Log.e("Description Loading", "Error Load Data Asynchronously");
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(Cursor data) {
                mDescrioptionCursor = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
/*
    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notify();
        }
        return temp;
    }*/
}