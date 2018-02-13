package me.kungfucat.tripsplit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment {

    public FriendFragment() {
        // Required empty public constructor
    }

    public static FriendFragment newInstance() {

        Bundle args = new Bundle();

        FriendFragment fragment = new FriendFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View row = inflater.inflate(R.layout.fragment_friend, container, false);
        ListView listView = row.findViewById(R.id.friendListView);
        listView.setAdapter(new ListFriendAdapter());

        return row;
    }

    class ListFriendAdapter extends BaseAdapter {
        ArrayList<String> arrayList;

        public ListFriendAdapter() {
            arrayList = new ArrayList<>();
            arrayList.add("+91 9282018810");
            arrayList.add("+91 8739103859");
            arrayList.add("+91 9813193848");
            arrayList.add("+91 9283923849");
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View viewRow = getLayoutInflater().inflate(R.layout.friend_custom_row, null);
            TextView textView = viewRow.findViewById(R.id.friendNameTextView);
            ImageView imageView = viewRow.findViewById(R.id.faceImageView);
            if (i % 4 == 0) {
                GlideApp.with(getContext())
                        .load(R.drawable.face1)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(imageView);
            } else if (i % 4 == 1) {
                GlideApp.with(getContext())
                        .load(R.drawable.face2)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(imageView);
            } else if (i % 4 == 2) {
                GlideApp.with(getContext())
                        .load(R.drawable.face3)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(imageView);
            } else if (i % 4 == 3) {
                GlideApp.with(getContext())
                        .load(R.drawable.face4)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(imageView);
            }
            textView.setText(arrayList.get(i));
            return viewRow;
        }
    }
}

