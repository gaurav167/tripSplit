package me.kungfucat.tripsplit;


import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
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
            ContentResolver cr = getContext().getContentResolver(); //Activity/Application android.content.Context
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                    if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                        while (pCur.moveToNext()) {
                            String contactNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            if (contactNumber.length() >= 8)
                                arrayList.add(contactNumber);
                            break;
                        }
                        pCur.close();
                    }

                } while (cursor.moveToNext());
            }
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

