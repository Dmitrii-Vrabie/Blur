package hfad.com.cribforpascal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hfad.com.cribforpascal.data.CribForPascalContract;

/**
 * Created by Mephisto on 3/4/2017.
 */

public class CribAdapted extends RecyclerView.Adapter<CribAdapted.ViewHolder>{
    private Context mContext;
    private Cursor mCursor;
    private TextView mStatementView;

    public CribAdapted(Context context, Cursor cursor){
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public CribAdapted.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.crib_list, parent, false);
        return new CribAdapted.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CribAdapted.ViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
        final String statement = mCursor.getString(mCursor.getColumnIndex(CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT));
        holder.mStatementView.setText(statement);
        holder.mStatementView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(v.getContext(), DetailActivity.class);

                detailIntent.putExtra("statementText", statement);
                mContext.startActivity(detailIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mStatementView;

        public ViewHolder(View itemView) {
            super(itemView);

            mStatementView = (TextView)itemView.findViewById(R.id.tv_statement);
        }
    }

}