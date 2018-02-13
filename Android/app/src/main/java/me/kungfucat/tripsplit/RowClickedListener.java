package me.kungfucat.tripsplit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by harsh on 2/13/18.
 */

public class RowClickedListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener clickListener;
    private GestureDetector gestureDetector;

    public RowClickedListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        clickListener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }


            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);

                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());

                if (childView != null && clickListener != null) {
                    clickListener.onLongItemClick(childView, recyclerView.getChildAdapterPosition(childView));
                }

            }
        });
    }


    public static ArrayList<HistoryModel> getTransactions(int userId) {
        ArrayList<HistoryModel> historyModels = new ArrayList<>();
        HistoryModel historyModel1 = new HistoryModel(),
                historyModel2 = new HistoryModel(),
                historyModel3 = new HistoryModel(),
                historyModel4 = new HistoryModel();

        historyModel1.setTimeStamp("02/13/18");
        historyModel1.setTransactionId(102);
        historyModel1.setGroupId(1);
        historyModel1.setUserId(1);
        historyModel1.setValue(123);

        historyModels.add(historyModel1);

        historyModel2.setTimeStamp("02/14/18");
        historyModel2.setTransactionId(104);
        historyModel2.setGroupId(2);
        historyModel2.setUserId(1);
        historyModel2.setValue(192);

        historyModels.add(historyModel2);


        historyModel3.setTimeStamp("02/17/18");
        historyModel3.setTransactionId(107);
        historyModel3.setGroupId(4);
        historyModel3.setUserId(1);
        historyModel3.setValue(-10);

        historyModels.add(historyModel3);

        historyModel4.setTimeStamp("02/18/18");
        historyModel4.setTransactionId(108);
        historyModel4.setGroupId(5);
        historyModel4.setUserId(1);
        historyModel4.setValue(-1002);

        historyModels.add(historyModel4);

        HistoryModel historyModel5 = new HistoryModel();

        historyModel5.setTimeStamp("02/19/18");
        historyModel5.setTransactionId(110);
        historyModel5.setGroupId(7);
        historyModel5.setUserId(1);
        historyModel5.setValue(102);

        historyModels.add(historyModel5);

        HistoryModel historyModel6 = new HistoryModel();

        historyModel6.setTimeStamp("02/20/18");
        historyModel6.setTransactionId(113);
        historyModel6.setGroupId(8);
        historyModel6.setUserId(1);
        historyModel6.setValue(203);

        historyModels.add(historyModel6);

        HistoryModel historyModel7 = new HistoryModel();

        historyModel7.setTimeStamp("02/21/18");
        historyModel7.setTransactionId(119);
        historyModel7.setGroupId(8);
        historyModel7.setUserId(1);
        historyModel7.setValue(-192);

        historyModels.add(historyModel5);

        HistoryModel historyModel8 = new HistoryModel();

        historyModel8.setTimeStamp("02/24/18");
        historyModel8.setTransactionId(124);
        historyModel8.setGroupId(10);
        historyModel8.setUserId(1);
        historyModel8.setValue(492);

        historyModels.add(historyModel8);

        HistoryModel historyModel9 = new HistoryModel();

        historyModel9.setTimeStamp("02/27/18");
        historyModel9.setTransactionId(135);
        historyModel9.setGroupId(11);
        historyModel9.setUserId(1);
        historyModel9.setValue(-381);

        historyModels.add(historyModel9);

        return historyModels;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
        }
        return false;


    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}

