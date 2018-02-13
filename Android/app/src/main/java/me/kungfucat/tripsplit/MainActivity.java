package me.kungfucat.tripsplit;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView creditValue, debitValue;
    float cv, dv;

    PieChart pieChart;
    SlidingUpPanelLayout slidingUpPanelLayout;
    SmartTabLayout smartViewPagerTab;
    ViewPager viewPager;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creditValue = findViewById(R.id.creditValue);
        debitValue = findViewById(R.id.debitValue);
        pieChart = findViewById(R.id.pieChart);
        slidingUpPanelLayout = findViewById(R.id.sliding_layout);
        viewPager = findViewById(R.id.viewPager);
        smartViewPagerTab = findViewById(R.id.viewpagertab);
        floatingActionButton = findViewById(R.id.fab);

        dv = 100.00f;
        cv = 50.00f;
        creditValue.setText(cv + "");
        debitValue.setText(dv + "");


        setUpPieChart();
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if (previousState == SlidingUpPanelLayout.PanelState.EXPANDED) {

                } else if (previousState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    pieChart.animateY(1500);
                    pieChart.invalidate();
                }
            }
        });
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), 1));
        smartViewPagerTab.setViewPager(viewPager);
        viewPager.setCurrentItem(1);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void setUpPieChart() {

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(cv, "Credit"));
        entries.add(new PieEntry(dv, "Debit"));

        PieDataSet set = new PieDataSet(entries, "Transactions");
        set.setColors(new int[]{Color.GREEN, Color.RED});
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        pieChart.invalidate();
        pieChart.animateY(1500);
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
                String weatherInfo = jsonObject.getString("data");
                JSONArray arr = new JSONArray(weatherInfo);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    Log.i("DebitedValue", jsonPart.getString("debitedValue"));
                    Log.i("CreditValue", jsonPart.getString("creditValue"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
