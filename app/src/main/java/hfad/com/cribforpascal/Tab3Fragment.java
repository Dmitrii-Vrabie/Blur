package hfad.com.cribforpascal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import hfad.com.cribforpascal.data.CribForPascalContract;
import hfad.com.cribforpascal.data.CribForPascalDBHelper;

/**
 * Created by Mephisto on 3/22/2017.
 */
public class Tab3Fragment extends Fragment {
    private static final String STATEMENT_KEY = "statementText";
    private SQLiteDatabase mDatabase;
    private ArrayList<String> mTokens;
    private TextView mExerciseView;
    private EditText mExerciseEditText;
    private String mAnswer;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        CribForPascalDBHelper dbHelper = CribForPascalDBHelper.getInstance(getContext());
        mDatabase = dbHelper.getReadableDatabase();
    }

    public void onStart() {
        super.onStart();
        new AsyncExercise().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_three, container, false);
        mExerciseView = (TextView) rootView.findViewById(R.id.tv_exerice_text_view);
        mExerciseEditText = (EditText) rootView.findViewById(R.id.exercise_edit_text);
        mExerciseEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if ((event != null && (event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (mAnswer.equals(mExerciseEditText.getText().toString())) {
                        mExerciseView.setBackgroundColor(getResources().getColor(R.color.light_green));
                    } else
                        mExerciseView.setBackgroundColor(getResources().getColor(R.color.light_red));
                }
                return false;
            }
        });
        return rootView;
    }

    public String textFromIntent() {
        Intent intent = getActivity().getIntent();
        String statement = intent.getStringExtra(STATEMENT_KEY);
        return statement;
    }

    private class AsyncExercise extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... params) {
            String statementText = textFromIntent();
            return mDatabase.query(CribForPascalContract.CribForPascalEntry.TABLE_NAME,
                    new String[]{CribForPascalContract.CribForPascalEntry.COLUMN_EXERICISE, CribForPascalContract.CribForPascalEntry.COLUMN_ANSWER},
                    CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT + "=?",
                    new String[]{statementText},
                    null,
                    null,
                    null);
            //rawQuery("select step_by_step from crib where statement = ?", new String[]{statementText});
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor.moveToFirst()) {
                do {
                    String exercise = cursor.getString(0);
                    mAnswer = cursor.getString(1);
                    mExerciseView.setText(exercise);
                } while (cursor.moveToNext());
            }
        }
    }
}
