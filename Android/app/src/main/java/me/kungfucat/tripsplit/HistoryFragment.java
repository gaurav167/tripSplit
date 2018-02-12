package me.kungfucat.tripsplit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {


    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance(int userId) {

        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        args.putInt("userId", userId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View row = inflater.inflate(R.layout.fragment_history, container, false);
        Bundle bundle = getArguments();
        int userId = bundle.getInt("userId");

        ArrayList<HistoryModel> historyModels = getTransactions(userId);
        RecyclerView recyclerView = row.findViewById(R.id.historyRecyclerView);
        HistoryRecyclerViewAdapter historyRecyclerViewAdapter = new HistoryRecyclerViewAdapter(getContext(), historyModels);
        recyclerView.setAdapter(historyRecyclerViewAdapter);
        return row;
    }

    public ArrayList<HistoryModel> getTransactions(int userId) {
        ArrayList<HistoryModel> historyModels = new ArrayList<>();
        HistoryModel historyModel1 = new HistoryModel(),
                historyModel2 = new HistoryModel(),
                historyModel3 = new HistoryModel();

        historyModel1.setTimeStamp("02/13/18");
        historyModel1.setTransactionId(102);
        historyModel1.setGroupId(1);
        historyModel1.setUserId(1);
        historyModel1.setValue(123);

        historyModels.add(historyModel1);

        historyModel2.setTimeStamp("03/13/18");
        historyModel2.setTransactionId(104);
        historyModel2.setGroupId(2);
        historyModel2.setUserId(1);
        historyModel2.setValue(192);

        historyModels.add(historyModel2);


        historyModel3.setTimeStamp("03/13/18");
        historyModel3.setTransactionId(104);
        historyModel3.setGroupId(2);
        historyModel3.setUserId(1);
        historyModel3.setValue(192);

        historyModels.add(historyModel3);

        historyModel2.setTimeStamp("05/13/18");
        historyModel2.setTransactionId(105);
        historyModel2.setGroupId(3);
        historyModel2.setUserId(1);
        historyModel2.setValue(12);

        historyModels.add(historyModel3);

        return historyModels;
    }
}
