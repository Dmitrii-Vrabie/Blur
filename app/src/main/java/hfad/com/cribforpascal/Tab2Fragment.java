package hfad.com.cribforpascal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import hfad.com.cribforpascal.data.CribForPascalContract;
import hfad.com.cribforpascal.data.CribForPascalDBHelper;

/**
 * Created by Mephisto on 3/22/2017.
 */
public class Tab2Fragment extends Fragment {
    private static final String STATEMENT_KEY = "statementText";
    private static final String TAG = "TAB 2 Fragment";
    private ArrayList<String> mTokens;
    private TextView mSBSTextView;
    private TextView mSBSTitle;
    private SQLiteDatabase mDatabase;
    private Button mAddButton;
    private Button mRemoveButton;
    private String firstPartOfText = "";
    private int currentPosition = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CribForPascalDBHelper dbHelper = CribForPascalDBHelper.getInstance(getContext());
        mDatabase = dbHelper.getReadableDatabase();
    }

    @Override
    public void onStart() {
        super.onStart();
        new OneMoreAsync().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_two, container, false);
        mSBSTitle = (TextView)rootView.findViewById(R.id.tv_sbs_title);
        mSBSTextView = (TextView) rootView.findViewById(R.id.tv_step_by_step);
        mSBSTextView.setText(firstPartOfText);
        mAddButton = (Button) rootView.findViewById(R.id.tab_2_btn_add);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition != mTokens.size()) {
                    String partOfText = mTokens.get(currentPosition);
                    firstPartOfText = firstPartOfText + partOfText;
                    mSBSTextView.setText(firstPartOfText);
                    currentPosition++;
                }
            }
        });
        mRemoveButton = (Button) rootView.findViewById(R.id.tab_2_btn_remove);
        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = 0;
                mSBSTextView.setText("");
                firstPartOfText = "";
            }
        });
        return rootView;
    }

    public String textFromIntent() {
        Intent intent = getActivity().getIntent();
        String statement = intent.getStringExtra(STATEMENT_KEY);
        return statement;
    }

    private class OneMoreAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... params) {
            String statementText = textFromIntent();
            return mDatabase.query(CribForPascalContract.CribForPascalEntry.TABLE_NAME,
                    new String[]{CribForPascalContract.CribForPascalEntry.COLUMN_STEP_BY_STEP},
                    CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT + "=?",
                    new String[]{statementText},
                    null,
                    null,
                    null);
            //rawQuery("select step_by_step from crib where statement = ?", new String[]{statementText});
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            mTokens = new ArrayList<>();
            String textToSplit = "";
            if (cursor.moveToFirst()) {
                do {
                    textToSplit = cursor.getString(0);
                } while (cursor.moveToNext());

                for (String token : textToSplit.split("//")) {
                    mTokens.add(token);
                }
            }
        }
    }
}
