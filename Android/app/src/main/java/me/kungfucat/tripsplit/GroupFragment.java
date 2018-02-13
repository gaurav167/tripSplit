package me.kungfucat.tripsplit;


import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {

    public static String URL_BACKEND = "pysplit.pythonanywhere.com";
    public static ArrayList<GroupModel> arrayList;

    public GroupFragment() {
        // Required empty public constructor
        MyTask myTask = new MyTask();
        myTask.execute(URL_BACKEND);
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
        arrayList = getGroupArrayList();
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
        GroupFragmentTask task = new GroupFragmentTask();
        task.execute(URL_BACKEND, "string_id");
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


    class MyTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                ContentValues values = new ContentValues();
                values.put("valueId", strings[2]);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                sb.append(URLEncoder.encode("data", "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(strings[2], "UTF-8"));
                writer.write(sb.toString());
                writer.flush();
                writer.close();
                os.close();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {

                JSONObject jsonObject = new JSONObject(result);
                String weatherInfo = jsonObject.getString("weather");
                JSONArray arr = new JSONArray(weatherInfo);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    Log.i("testing", jsonPart.getString("main"));
                    Log.i("testing", jsonPart.getString("description"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class GroupFragmentTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                ContentValues values = new ContentValues();
                values.put("valueId", strings[2]);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                sb.append(URLEncoder.encode("data", "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(strings[2], "UTF-8"));
                writer.write(sb.toString());
                writer.flush();
                writer.close();
                os.close();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {

                JSONObject jsonObject = new JSONObject(result);
                String weatherInfo = jsonObject.getString("weather");
                JSONArray arr = new JSONArray(weatherInfo);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    Log.i("testing", jsonPart.getString("main"));
                    Log.i("testing", jsonPart.getString("description"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
