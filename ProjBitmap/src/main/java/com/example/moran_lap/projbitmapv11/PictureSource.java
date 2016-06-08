package com.example.moran_lap.projbitmapv11;

import android.graphics.Bitmap;

/**
 * Created by Gili on 25/04/2016.
 */
public class PictureSource extends ImageSource {

    public PictureSource(){
        sourceName = "Image";
    }

    @Override
    public Bitmap getImage() {
        return originalSourceBitmap;
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
