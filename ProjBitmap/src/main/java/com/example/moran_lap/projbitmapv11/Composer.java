package com.example.moran_lap.projbitmapv11;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Gili on 08/04/2016.
 */
public class Composer extends Service {

    private Bitmap mBackgroundBitmap;
    private Bitmap mPreviewBitmap;
    private ImageView mImageView;
    private ArrayList<SurfaceComponent> mSurfaceComponents;
    private Object mObj;

    public Composer() {
        mSurfaceComponents = new ArrayList();
        mObj = new Object();
        initPreview();
    }

    private void initPreview() {
        mImageView = (ImageView) ApplicationContext.getActivity().findViewById(R.id.imageView);
        initBitmap();

    }

    public ImageView getImageView() {
        return mImageView;
    }

    void initBitmap() {
        if (mBackgroundBitmap == null) {
            mBackgroundBitmap = Bitmap.createBitmap(1280, 720, Bitmap.Config.ARGB_8888);
            mBackgroundBitmap.eraseColor(Color.BLACK);
        }
        if (mPreviewBitmap == null) {
            mPreviewBitmap = Bitmap.createBitmap(1280, 720, Bitmap.Config.ARGB_8888);
            mPreviewBitmap.eraseColor(Color.BLACK);
            mImageView.setImageBitmap(mPreviewBitmap);
        }
    }

    public ArrayList<SurfaceComponent> getmSurfaceComponents() {
        return mSurfaceComponents;
    }

    public Bitmap getBackgroundBitmap() {
        return mBackgroundBitmap;
    }

    public Bitmap getmPreviewBitmap() {
        return mPreviewBitmap;
    }
    public void setPreviewBitmap(Bitmap mPreviewBitmap) {
        this.mPreviewBitmap = mPreviewBitmap;
    }

    private boolean isRunning;
    private Context context;
    private Thread backgroundThread;
    private MainActivity mainActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        this.isRunning = false;
        this.context = this;
        this.backgroundThread = new Thread(myTask);
        this.mainActivity = (MainActivity) ApplicationContext.getActivity();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this,"Service Started",Toast.LENGTH_LONG).show();
        //refreshSurfaceComponentsOnBitmap();
        if (!this.isRunning) {
            this.isRunning = true;
            this.backgroundThread.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // super.onDestroy();
        this.isRunning = false;
        // Toast.makeText(this,"Service Stopped",Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Runnable myTask = new Runnable() {
        @Override
        public void run() {
            //Toast.makeText(ApplicationContext.getActivity(),"Service is running" + i,Toast.LENGTH_LONG).show();
            //i++;
            //mainActivity.refreshSurfaceComponentsOnBitmap();
            stopSelf();
        }
    };
}


