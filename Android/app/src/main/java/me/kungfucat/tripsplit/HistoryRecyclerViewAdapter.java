package me.kungfucat.tripsplit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by harsh on 2/13/18.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.HistoryViewHolder> {
    ArrayList<HistoryModel> historyModels;
    Context context;

    public HistoryRecyclerViewAdapter() {


    }

    public HistoryRecyclerViewAdapter(Context context, ArrayList<HistoryModel> historyModels) {
        this.historyModels = historyModels;
        this.context = context;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_custom_row, parent, false);
        HistoryViewHolder holder = new HistoryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        TextView groupName = holder.groupName;
        TextView timeStamp = holder.timeStamp;
        TextView amount = holder.amount;
        Log.d("TAG",historyModels.get(position).getValue()+"");

        String groupNameString = "ABCD ";
        groupName.setText(groupNameString + historyModels.get(position).getGroupId());
        timeStamp.setText(historyModels.get(position).getTimeStamp());
        amount.setText(historyModels.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return historyModels.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView groupName, timeStamp, amount;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.groupNameTextView);
            timeStamp = itemView.findViewById(R.id.timeStamp);
            amount = itemView.findViewById(R.id.transactionAmount);
        }
    }
}
