package hfad.com.cribforpascal.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mephisto on 4/18/2017.
 */

public class AsyncCrib extends AsyncTask<String, Void, Cursor> {
    private static final String TAG = "AsyncCrib";
    private SQLiteDatabase mDatabase;
    private Context mContext;
    private Cursor mCursor;

    public AsyncCrib(Context context) {
        mContext = context;
    }

    protected Cursor doInBackground(String... params) {
        ArrayList<String> mTest = new ArrayList<>(Arrays.asList(params));
        String column = mTest.get(0);
        String selectionArgs = mTest.get(1).toLowerCase();
        CribForPascalDBHelper dbHelper = new CribForPascalDBHelper(mContext);
        mDatabase = dbHelper.getReadableDatabase();
        Log.i(TAG, "doInBackground: " + column + " and " + selectionArgs);
        return mDatabase.query(CribForPascalContract.CribForPascalEntry.TABLE_NAME,
                new String[]{CribForPascalContract.CribForPascalEntry.COLUMN_STEP_BY_STEP},
                CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT + "=?",
                new String[]{selectionArgs},
                null,
                null,
                null);
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
       try {
           mCursor = cursor;


            do {
                String test = mCursor.getString(0);
                Log.i(TAG, "onPostExecute: " + test);
            } while (mCursor.moveToNext());

        } catch (Exception e){
           e.printStackTrace();
       }
    }
}
