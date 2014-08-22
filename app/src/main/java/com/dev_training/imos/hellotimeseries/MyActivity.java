package com.dev_training.imos.hellotimeseries;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.FrameLayout;

import java.util.Random;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my, container, false);

            // id=frameLayoutにGraphViewを挿入
            FrameLayout frameLayout
                    = (FrameLayout) rootView.findViewById(R.id.frameLayout);

            ValueWithTimestampList sampleData = new ValueWithTimestampList();

            // サンプルのデータを生成
            Random rand = new Random();
            for (int i=0; i<100; i++){
                ValueWithTimestamp valueWithTimestamp = new ValueWithTimestamp();
                // 現時刻から1秒ごとのサンプルを作成
                valueWithTimestamp.timestamp = System.currentTimeMillis() + i * 1000;
                valueWithTimestamp.value = rand.nextInt(256);
                sampleData.add(valueWithTimestamp);
            }

            // GraphViewの生成と代入
            GraphView graphView = new GraphView(getActivity(), sampleData);


            frameLayout.addView(graphView);

            return rootView;
        }


    }
}
