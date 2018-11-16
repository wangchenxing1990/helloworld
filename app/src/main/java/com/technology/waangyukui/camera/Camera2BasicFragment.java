package com.technology.waangyukui.camera;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.technology.waangyukui.mycyclerapp.Manifest;
import com.technology.waangyukui.mycyclerapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by lenvo on 2018/6/27.
 */

public class Camera2BasicFragment extends Fragment implements View.OnClickListener {

    private AutoFitTextureView mTextureView;
    private File mFile;
    private HandlerThread mBackgrokundThread;
    private Handler mHandler;
    private String mCamreaId;
    private ImageReader mImageReader;
    private int mSensorOrientation;
    private boolean mFlashSupported;
    private Size mPreviewSize;
    private static final String FRAGMENT_DIALOG="dialog";
    private static final int REQUEST_CAMERA_PERMISSION=1;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    private static final int MAX_PREVIEW_WIDTH = 1920;
    private static final int MAX_PREVIEW_HEIGHT = 1080;

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    public static Fragment Instance() {
        return new Camera2BasicFragment();
    }

    private ImageReader.OnImageAvailableListener onImageAvailableListener = new ImageReader.OnImageAvailableListener() {
        @Override
        public void onImageAvailable(ImageReader imageReader) {
            mHandler.post(new ImageSaver(imageReader.acquireNextImage(), mFile));
        }
    };

    TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
            openCamera(i, i1);
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
//            configuretransform(i, i1);
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

        }
    };

    CameraDevice.StateCallback mStateCallBack = new CameraDevice.StateCallback() {

        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {

        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {

        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.basic_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.picture).setOnClickListener(this);
        view.findViewById(R.id.info).setOnClickListener(this);

        mTextureView = view.findViewById(R.id.texture);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFile = new File(getActivity().getExternalFilesDir(null), "pic.jpg");
    }

    @Override
    public void onResume() {
        super.onResume();
        startBackgroundThread();
        if (mTextureView.isAvailable()) {
            openCamera(mTextureView.getWidth(), mTextureView.getHeight());
        } else {
            mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);
        }
    }

    private void openCamera(int width, int height) {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermissions();
            return;
        }
        setUpCameraOutputs(width, height);
        configureCameraTransform(width, height);
        Activity activity = getActivity();

        CameraManager cameraManager = (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);
//        try {
////            if () {
////
////            }
//            cameraManager.openCamera(mCamreaId, mStateCallBack, mHandler);
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            throw new RuntimeException("Interrupted while trying to lock camera opening.", e);
//        }
    }

    private void configureCameraTransform(int width, int height) {


    }

    private void setUpCameraOutputs(int width, int height) {
        Activity activity = getActivity();
        CameraManager manager = (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);
        try {
            for (String cameraId : manager.getCameraIdList()) {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
                if (facing != null && facing == CameraCharacteristics.LENS_FACING_FRONT) {
                    continue;
                }

                StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                if (map == null) {
                    continue;
                }

                Size largest = Collections.max(Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)), new CompareSizesByArea());
                mImageReader = ImageReader.newInstance(largest.getWidth(), largest.getHeight(), ImageFormat.JPEG, 2);
                mImageReader.setOnImageAvailableListener(onImageAvailableListener, mHandler);
                int displayRotation = activity.getWindowManager().getDefaultDisplay().getRotation();
                mSensorOrientation = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
                boolean swappedDimensions = false;
                switch (displayRotation) {
                    case Surface.ROTATION_0:

                    case Surface.ROTATION_180:
                        if (mSensorOrientation == 90 || mSensorOrientation == 270) {
                            swappedDimensions = true;
                        }
                        break;
                    case Surface.ROTATION_90:
                    case Surface.ROTATION_270:
                        if (mSensorOrientation == 0 || mSensorOrientation == 180) {
                            swappedDimensions = true;
                        }
                        break;
                }
                Point point = new Point();
                activity.getWindowManager().getDefaultDisplay().getSize(point);
                int rotationPreviewWidth = width;
                int rotationPreviewHeight = height;
                int maxPreviewWidth = point.x;
                int maxPreviewHeight = point.y;
                if (swappedDimensions) {
                    rotationPreviewHeight = width;
                    rotationPreviewWidth = height;
                    maxPreviewHeight = point.x;
                    maxPreviewWidth = point.y;
                }
                if (maxPreviewWidth > MAX_PREVIEW_WIDTH) {
                    maxPreviewWidth = MAX_PREVIEW_WIDTH;
                }
                if (maxPreviewHeight > MAX_PREVIEW_HEIGHT) {
                    maxPreviewHeight = MAX_PREVIEW_HEIGHT;
                }
                mPreviewSize = chooseOptimalSize(map.getOutputSizes(SurfaceView.class), rotationPreviewHeight, rotationPreviewWidth, maxPreviewHeight, maxPreviewWidth, largest);
                int orientation = getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    mTextureView.setAspectRatio(mPreviewSize.getWidth(), mPreviewSize.getHeight());
                } else {
                    mTextureView.setAspectRatio(mPreviewSize.getHeight(), mPreviewSize.getWidth());
                }
                Boolean available = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                mFlashSupported = (available == null ? false : available);
                mCamreaId = cameraId;
                return;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private Size chooseOptimalSize(Size[] outputSizes, int rotationPreviewHeight, int rotationPreviewWidth, int maxPreviewHeight, int maxPreviewWidth, Size largest) {
        List<Size> bigEnough = new ArrayList<>();
        List<Size> notBigEnough = new ArrayList<>();
        int w = largest.getWidth();
        int h = largest.getHeight();
        for (Size option : outputSizes) {
            if (option.getHeight() <= maxPreviewHeight && option.getWidth() <= maxPreviewWidth
                    && option.getHeight() == option.getWidth() * h / w) {
                if (option.getWidth() >= rotationPreviewWidth && option.getHeight() >= rotationPreviewHeight) {
                    bigEnough.add(option);
                } else {
                    notBigEnough.add(option);
                }
            }
        }

        if (bigEnough.size() > 0) {
            return Collections.min(bigEnough, new CompareSizesByArea());
        } else if (notBigEnough.size() > 0) {
            return Collections.max(notBigEnough, new CompareSizesByArea());
        } else {
            return outputSizes[0];
        }
    }

    private void requestCameraPermissions() {
      if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
         new ConfirmationDialog().show(getChildFragmentManager(),FRAGMENT_DIALOG);
      }else{
          requestPermissions(new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA_PERMISSION);
      }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    class CompareSizesByArea implements Comparator<Size> {
        @Override
        public int compare(Size size, Size t1) {
            return Long.signum((long) size.getHeight() * size.getHeight() - (long) t1.getWidth() * t1.getHeight());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopBackgroundThread();
    }

    private void stopBackgroundThread() {
        mBackgrokundThread.quitSafely();
        try {
            mBackgrokundThread.join();
            mBackgrokundThread = null;
            mHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startBackgroundThread() {
        mBackgrokundThread = new HandlerThread("CameraBackground");
        mBackgrokundThread.start();
        mHandler = new Handler(mBackgrokundThread.getLooper());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.picture:
                break;
            case R.id.info:
                break;
        }
    }

    private class ImageSaver implements Runnable {
        private final Image mImage;
        private final File mFile;
        public ImageSaver(Image image, File file) {
            this.mImage = image;
            this.mFile = file;
        }

        @Override
        public void run() {
            ByteBuffer buffer = mImage.getPlanes()[0].getBuffer();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(mFile);
                fileOutputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class ConfirmationDialog extends DialogFragment{
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final  Fragment parent=getParentFragment();
            return new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.request_permission)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                         parent.requestPermissions(new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA_PERMISSION);
                        }
                    })
                    .setNegativeButton(android.R.string.cancel,new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                           Activity activity=getActivity();
                            if (activity!=null){
                               activity.finish();
                           }
                        }
                    })
                    .create();
        }
    }
}
