package hfad.com.cribforpascal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import hfad.com.cribforpascal.data.CribForPascalDBHelper;
import hfad.com.cribforpascal.data.CribUtil;


public class MainActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private SQLiteDatabase mDatabase;
    private static final String TAG = "MyActivity";

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabase.close();
    }

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
        new FetchCribData().execute();
    }

    public class FetchCribData extends AsyncTask<Void, Void, Cursor>{
        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            mAdapter = new CribAdapted(getBaseContext(), cursor);
            mRecyclerView.setAdapter(mAdapter);
        }

        @Override
        protected Cursor doInBackground(Void... params) {
            Cursor cursor = null;
            cursor = mDatabase.rawQuery("select statement from crib", null);
            return cursor;
          }

    }
}
