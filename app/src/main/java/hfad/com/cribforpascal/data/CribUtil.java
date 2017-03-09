package hfad.com.cribforpascal.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mephisto on 3/4/2017.
 */

public class CribUtil {
    public static void DataForCrib(SQLiteDatabase db) {
        if(db == null) {
            return;
        }
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT, "IF");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_DESCRIPTION, "IF DESCRIPTION");
        list.add(cv);

        cv = new ContentValues();
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT, "DO");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_DESCRIPTION, "DO DESCRIPTION");
        list.add(cv);

        cv = new ContentValues();
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT, "FOR");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_DESCRIPTION, "FOR DESCRIPTION");
        list.add(cv);

        cv = new ContentValues();
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT, "WHILE");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_DESCRIPTION, "WHILE DESCRIPTION");
        list.add(cv);

        try {
            db.beginTransaction();
            db.delete(CribForPascalContract.CribForPascalEntry.TABLE_NAME, null, null);
            for (ContentValues c : list){
                db.insert(CribForPascalContract.CribForPascalEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }catch (SQLException e){
        }
        finally {
            db.endTransaction();
        }
    }



}
