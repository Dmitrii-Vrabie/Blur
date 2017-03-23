package hfad.com.cribforpascal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import hfad.com.cribforpascal.data.CribForPascalContract;
import hfad.com.cribforpascal.data.CribForPascalDBHelper;
import hfad.com.cribforpascal.data.CribUtil;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private RecyclerView mRecyclerView;
    public static final int TASK_LOADER_ID = 0;
    private RecyclerView.LayoutManager mLayoutManager;
    private CribAdapted mAdapter;
    private SQLiteDatabase mDatabase;
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list_items);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CribAdapted(this);
        mRecyclerView.setAdapter(mAdapter);


        CribForPascalDBHelper dbHelper = new CribForPascalDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        CribUtil.DataForCrib(mDatabase);
        getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {
            Cursor mTaskData = null;


            @Override
            protected void onStartLoading() {
                if (mTaskData != null) {
                    deliverResult(mTaskData);
                } else
                    forceLoad();
            }

            @Override
            public Cursor loadInBackground() {
                try {
                    return mDatabase.query(CribForPascalContract.CribForPascalEntry.TABLE_NAME,
                            new String[]{CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT},
                            null,
                            null,
                            null,
                            null,
                            CribForPascalContract.CribForPascalEntry._ID);
                } catch (Exception e) {
                    Log.e(TAG, "Error asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(Cursor data) {
                mTaskData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabase.close();
    }
}
