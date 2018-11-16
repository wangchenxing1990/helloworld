package com.technology.waangyukui.bluetooth;

import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.technology.waangyukui.mycyclerapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenvo on 2018/6/1.
 */

public class ScanResultAdapter extends BaseAdapter {
    private final Context applicationContext;
    private final LayoutInflater from;
    private  List<ScanResult> mArray;
    public ScanResultAdapter(Context applicationContext, LayoutInflater from) {
        this.applicationContext=applicationContext;
        this.from=from;
        mArray=new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mArray.size();
    }

    @Override
    public Object getItem(int i) {
        return mArray.get(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public long getItemId(int i) {
        return mArray.get(i).getDevice().getAddress().hashCode();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view=from.inflate(R.layout.listitem_scanresult,null);
        }
        TextView deviceNameView = (TextView) view.findViewById(R.id.device_name);
        TextView deviceAddressView = (TextView) view.findViewById(R.id.device_address);
        TextView lastSeenView = (TextView) view.findViewById(R.id.last_seen);

        ScanResult scanResult = mArray.get(i);

        String name = scanResult.getDevice().getName();
        if (name == null) {
            name = applicationContext.getResources().getString(R.string.no_name);
        }
        deviceNameView.setText(name);
        deviceAddressView.setText(scanResult.getDevice().getAddress());
        lastSeenView.setText(getTimeSinceString(applicationContext, scanResult.getTimestampNanos()));

        return null;
    }

    public void add(ScanResult scanResult) {

    }

    public static String getTimeSinceString(Context context, long timeNanoseconds) {
        String lastSeenText = context.getResources().getString(R.string.last_seen) + " ";

        long timeSince = SystemClock.elapsedRealtimeNanos() - timeNanoseconds;
        long secondsSince = TimeUnit.SECONDS.convert(timeSince, TimeUnit.NANOSECONDS);

        if (secondsSince < 5) {
            lastSeenText += context.getResources().getString(R.string.just_now);
        } else if (secondsSince < 60) {
            lastSeenText += secondsSince + " " + context.getResources()
                    .getString(R.string.seconds_ago);
        } else {
            long minutesSince = TimeUnit.MINUTES.convert(secondsSince, TimeUnit.SECONDS);
            if (minutesSince < 60) {
                if (minutesSince == 1) {
                    lastSeenText += minutesSince + " " + context.getResources()
                            .getString(R.string.minute_ago);
                } else {
                    lastSeenText += minutesSince + " " + context.getResources()
                            .getString(R.string.minutes_ago);
                }
            } else {
                long hoursSince = TimeUnit.HOURS.convert(minutesSince, TimeUnit.MINUTES);
                if (hoursSince == 1) {
                    lastSeenText += hoursSince + " " + context.getResources()
                            .getString(R.string.hour_ago);
                } else {
                    lastSeenText += hoursSince + " " + context.getResources()
                            .getString(R.string.hours_ago);
                }
            }
        }

        return lastSeenText;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int getPosition(String address) {
        int position = -1;
        for (int i = 0; i < mArray.size(); i++) {
            if (mArray.get(i).getDevice().getAddress().equals(address)) {
                position = i;
                break;
            }
        }
        return position;
    }
}
