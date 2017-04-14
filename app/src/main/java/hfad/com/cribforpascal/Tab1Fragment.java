package hfad.com.cribforpascal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hfad.com.cribforpascal.data.CribForPascalDBHelper;

/**
 * Created by Mephisto on 3/22/2017.
 */
public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab 1 Fragment";
    private static final String STATEMENT_KEY = "statementText";
    public static String sTextFromIntent = "";
    private Cursor mDescrioptionCursor;
    private SQLiteDatabase mDatabase;
    public static final int DESCRIPTION_LOADER = 1;
    private TextView tvDescription;
    private String statementDescription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CribForPascalDBHelper dbHelper = new CribForPascalDBHelper(getContext());
        mDatabase = dbHelper.getReadableDatabase();
    }

    @Override
    public void onStart() {
        super.onStart();
        new AsyncLoading().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        tvDescription = (TextView)rootView.findViewById(R.id.tv_description_fragment);
        return rootView;
    }

    private class AsyncLoading extends AsyncTask<Void, Void, Cursor> {


        @Override
        protected Cursor doInBackground(Void... params) {
            String statementText = textForDescription();
            Log.i(TAG, "doInBackground: " + statementText);
            return mDatabase.rawQuery("select description from crib where statement = ?", new String[] { statementText});
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            mDescrioptionCursor = cursor;
           if( mDescrioptionCursor.moveToFirst()){
               do {
                   String data = mDescrioptionCursor.getString(0);
                   tvDescription.setText(data);
               } while (mDescrioptionCursor.moveToNext());
           }
        }
    }

    private String textForDescription() {
        Intent intent = getActivity().getIntent();
        String description = intent.getStringExtra(STATEMENT_KEY);
        Log.i(TAG, "textForDescription: " + description);
        return description;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDescrioptionCursor.close();
        mDatabase.close();
    }
}