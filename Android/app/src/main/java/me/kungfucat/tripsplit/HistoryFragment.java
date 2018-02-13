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
import java.util.Random;

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
        HistoryFragmentTask task = new HistoryFragmentTask();
        task.execute();
        ListView listView = row.findViewById(R.id.historyListView);
        ArrayList<HistoryModel> historyModels = RowClickedListener.getTransactions(userId);
        HistoryAdapter adapter = new HistoryAdapter(getContext(), historyModels);
        listView.setAdapter(adapter);
        return row;
    }

    class HistoryAdapter extends BaseAdapter {

        ArrayList<HistoryModel> arrayList;
        Context context;

        public HistoryAdapter(Context context, ArrayList<HistoryModel> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
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
            View viewRow = getLayoutInflater().inflate(R.layout.history_custom_row, null);
            TextView transaction = viewRow.findViewById(R.id.transactionAmount);
            TextView timeStamp = viewRow.findViewById(R.id.timeStamp);
            TextView groupNameTextView = viewRow.findViewById(R.id.groupNameTextView);

            transaction.setText(arrayList.get(i).getValue() + "");
            timeStamp.setText(arrayList.get(i).getTimeStamp() + "");
            String[] GroupNames = {"Food", "Travel", "Party"};
            groupNameTextView.setText("" + GroupNames[getVal()]);
            return viewRow;
        }
    }

    public int getVal() {
        Random random = new Random();
        int values = random.nextInt(3);
        return values;
    }

    class HistoryFragmentTask extends AsyncTask<String, Void, String> {


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
                String data = jsonObject.getString("id");
                JSONArray arr = new JSONArray(data);

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
