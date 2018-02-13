package me.kungfucat.tripsplit;

import android.view.View;

/**
 * Created by harsh on 2/13/18.
 */

public interface OnItemClickListener {
    public void onItemClick(View view, int position);

    public void onLongItemClick(View view, int position);
}

