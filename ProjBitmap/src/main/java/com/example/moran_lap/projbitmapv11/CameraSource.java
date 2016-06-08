package com.example.moran_lap.projbitmapv11;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by Moran-Lap on 23/04/2016.
 */
public class CameraSource extends ImageSource {

    Camera2BasicFragment camera2BasicFragment;

    public CameraSource() {
        sourceName = "Camera";
        camera2BasicFragment = Camera2BasicFragment.newInstance();
        ApplicationContext.getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.container, camera2BasicFragment)
                .commit();
    }


    @Override
    public Bitmap getImage() {
        AutoFitTextureView v2;
        v2 = (AutoFitTextureView)ApplicationContext.getActivity().findViewById(R.id.texture);
        Bitmap bitmap1 = v2.getBitmap();
        return bitmap1;
    }

    @Override
    public void OpenSource() {

    }

    @Override
    public void CloseSource() {

    }

    @Override
    public void SetupSource() {

    }

    @Override
    public void EditSource() {

    }
}
