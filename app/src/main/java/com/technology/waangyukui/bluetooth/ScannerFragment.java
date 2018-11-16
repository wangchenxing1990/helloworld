package com.technology.waangyukui.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.technology.waangyukui.mycyclerapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenvo on 2018/6/1.
 */

public class ScannerFragment extends ListFragment {
    private ScanResultAdapter scanResultAdapter;
    private Handler mHandler;
    private BluetoothAdapter mAdapter;
    private BluetoothLeScanner bluetoothLeScanner;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setBluetoothAdapter(BluetoothAdapter adapter) {
        this.mAdapter = adapter;
        bluetoothLeScanner = mAdapter.getBluetoothLeScanner();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        scanResultAdapter = new ScanResultAdapter(getActivity().getApplicationContext(), LayoutInflater.from(getContext()));
        mHandler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setListAdapter(scanResultAdapter);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(null);
        getListView().setDividerHeight(0);
        setEmptyText(getString(R.string.empty_list));

        startScanning();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.scan_menu, menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                startScanning();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final long SCAN_PERIOD = 5000;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startScanning() {
        if (sampleScanCallback == null) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    stopScanning();
                }
            }, SCAN_PERIOD);

            sampleScanCallback = new SampleScanCallback();
            bluetoothLeScanner.startScan(buildScanFilters(), buildScanSetting(), sampleScanCallback);
            String toastText = getString(R.string.scan_start_toast) + " "
                    + TimeUnit.SECONDS.convert(SCAN_PERIOD, TimeUnit.MILLISECONDS) + " "
                    + getString(R.string.seconds);
            Toast.makeText(getActivity(), toastText, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), R.string.already_scanning, Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ScanSettings buildScanSetting() {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        builder.setScanMode(ScanSettings.SCAN_MODE_LOW_POWER);
        return builder.build();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private List<ScanFilter> buildScanFilters() {
        List<ScanFilter> scanList = new ArrayList();
        ScanFilter.Builder scanFilter = new ScanFilter.Builder();
        scanFilter.setServiceUuid(Constants.Service_UUID);
        scanList.add(scanFilter.build());
        return scanList;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void stopScanning() {
        bluetoothLeScanner.stopScan(sampleScanCallback);
        sampleScanCallback = null;
        scanResultAdapter.notifyDataSetChanged();
    }

    private SampleScanCallback sampleScanCallback;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private class SampleScanCallback extends ScanCallback {
        @Override
        public void onBatchScanResults(List<android.bluetooth.le.ScanResult> results) {
            super.onBatchScanResults(results);
            for (android.bluetooth.le.ScanResult scanResult : results) {
                scanResultAdapter.add(scanResult);
            }
            scanResultAdapter.notifyDataSetChanged();
        }

        @Override
        public void onScanResult(int callbackType, android.bluetooth.le.ScanResult result) {
            super.onScanResult(callbackType, result);
            scanResultAdapter.add(result);
            scanResultAdapter.notifyDataSetChanged();
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            Toast.makeText(getActivity(), "Scan failed with error: " + errorCode, Toast.LENGTH_LONG)
                    .show();
        }
    }
}
