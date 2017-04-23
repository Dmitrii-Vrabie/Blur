package hfad.com.cribforpascal.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mephisto on 3/4/2017.
 */

public class CribForPascalDBHelper extends SQLiteOpenHelper {
    private static CribForPascalDBHelper mInstance = null;

    private static final String DATABASE_NAME = "crib.db";
    private static final int DATABASE_VERSION = 5;


    public static CribForPascalDBHelper getInstance(Context context){
        if (mInstance == null){
            mInstance = new CribForPascalDBHelper(context.getApplicationContext());
        }
        return mInstance;
    }
    public CribForPascalDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CRIB_TABLE = "CREATE TABLE " +
                CribForPascalContract.CribForPascalEntry.TABLE_NAME + " (" +
                CribForPascalContract.CribForPascalEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CribForPascalContract.CribForPascalEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT + " TEXT NOT NULL, " +
                CribForPascalContract.CribForPascalEntry.COLUMN_STEP_BY_STEP + " TEXT NOT NULL, " +
                CribForPascalContract.CribForPascalEntry.COLUMN_EXERICISE + " TEXT NOT NULL, " +
                CribForPascalContract.CribForPascalEntry.COLUMN_ANSWER  + ");";
        db.execSQL(SQL_CREATE_CRIB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CribForPascalContract.CribForPascalEntry.TABLE_NAME);
        onCreate(db);
    }

}
