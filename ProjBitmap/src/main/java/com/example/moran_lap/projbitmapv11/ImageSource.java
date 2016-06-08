package com.example.moran_lap.projbitmapv11;

import android.graphics.Bitmap;

/**
 * Created by Gili on 08/04/2016.
 */
public abstract class ImageSource {

    protected String sourceName;
    protected Bitmap originalSourceBitmap;

    //protected ImageReader imageReader;

    public ImageSource(){
        //imageReader = ...;
    }

    public Bitmap getOriginalSourceBitmap() {
        return originalSourceBitmap;
    }

    public void setOriginalSourceBitmap(Bitmap originalSourceBitmap) {
        this.originalSourceBitmap = originalSourceBitmap;
    }

    public String getSourceName() {
        return sourceName;
    }

    // abstract methods
    public abstract Bitmap getImage();

    public abstract void OpenSource();

    public abstract void CloseSource();

    public abstract void SetupSource();//init the source

    public abstract void EditSource();
}
