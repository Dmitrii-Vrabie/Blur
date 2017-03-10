package hfad.com.cribforpascal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import hfad.com.cribforpascal.data.CribForPascalContract;
import hfad.com.cribforpascal.data.CribForPascalDBHelper;

public class DetailActivity extends AppCompatActivity {
    private TextView mDetailView;
    private SQLiteDatabase mDatabase;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        CribForPascalDBHelper dbHelper = new CribForPascalDBHelper(getApplicationContext());
        mDatabase = dbHelper.getWritableDatabase();

        mDetailView = (TextView)findViewById(R.id.tv_detail_activity);
        Intent intent = getIntent();
        String statementText = intent.getStringExtra("statementText");

        cursor = mDatabase.rawQuery("select description from crib where statement = ?", new String[] { statementText });
        if (cursor.moveToFirst())
        {
            do
            {
                String path = cursor.getString(0);
                mDetailView.setText(path);
            } while (cursor.moveToNext());
        }
        cursor.close();

    }
}
