package hfad.com.cribforpascal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.concurrent.TimeUnit;

import hfad.com.cribforpascal.data.CribForPascalContract;
import hfad.com.cribforpascal.data.CribUtil;
import hfad.com.cribforpascal.data.CribForPascalDBHelper;


public class MainActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list_items);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        CribForPascalDBHelper dbHelper = new CribForPascalDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        CribUtil.DataForCrib(mDatabase);
        Cursor cursor = getAllStatements();
        mAdapter = new CribAdapted(this, cursor);
        mRecyclerView.setAdapter(mAdapter);
    }

    private Cursor getAllStatements() {
        return mDatabase.query(
                CribForPascalContract.CribForPascalEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                CribForPascalContract.CribForPascalEntry._ID);
    }
}
