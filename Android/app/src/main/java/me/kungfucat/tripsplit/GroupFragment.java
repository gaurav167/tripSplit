package me.kungfucat.tripsplit;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {


    public GroupFragment() {
        // Required empty public constructor
    }

    public static GroupFragment newInstance() {

        Bundle args = new Bundle();
        GroupFragment fragment = new GroupFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View row = inflater.inflate(R.layout.fragment_group, container, false);
        ListView listView = row.findViewById(R.id.groupListView);
        ArrayList<GroupModel> arrayList = getGroupArrayList();
        GroupAdapter adapter = new GroupAdapter(getContext(), arrayList);
        listView.setAdapter(adapter);
        return row;
    }

    public ArrayList<GroupModel> getGroupArrayList() {
        ArrayList<GroupModel> arrayList = new ArrayList<>();
        GroupModel model1 = new GroupModel();
        model1.setName("Flight 807");
        model1.setStatus("Borrowed");

        GroupModel model2 = new GroupModel();
        model2.setName("Pizza");
        model2.setStatus("Settled");

        GroupModel model3 = new GroupModel();
        model3.setName("Birthday Party!");
        model3.setStatus("Lend");

        arrayList.add(model1);
        arrayList.add(model2);
        arrayList.add(model3);
        return arrayList;
    }

    class GroupAdapter extends BaseAdapter {
        Context context;
        ArrayList<GroupModel> arrayList;

        public GroupAdapter(Context context, ArrayList<GroupModel> arrayList) {
            this.arrayList = arrayList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return arrayList.size();
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
            View row = getLayoutInflater().inflate(R.layout.group_custom_row, null);
            TextView groupName = row.findViewById(R.id.groupNameTextView);
            ImageView groupIcon = row.findViewById(R.id.groupImageView);
            TextView status = row.findViewById(R.id.groupStatus);
            if (i % 3 == 0) {

                GlideApp.with(context)
                        .load(R.drawable.airplane)
                        .into(groupIcon);
            } else if (i % 3 == 1) {
                GlideApp.with(context)
                        .load(R.drawable.pizza)
                        .into(groupIcon);
            } else if (i % 3 == 2) {
                GlideApp.with(context)
                        .load(R.drawable.gift)
                        .into(groupIcon);
            }
            groupName.setText(arrayList.get(i).getName());
            status.setText(arrayList.get(i).getStatus());
            return row;
        }
    }

}
