package com.example.moran_lap.projbitmapv11;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Gili on 08/04/2016.
 */
public class SurfaceComponent extends Thread{

    private boolean isEnabled;
    private ImageSource imageSource;
    private Bitmap surfaceComponentBitmap;
    private Position imagePositionOnSurface;

    public SurfaceComponent(ImageSource source, Position position){
        this.isEnabled = true;
        this.imageSource = source;
        this.imagePositionOnSurface = position;
        this.surfaceComponentBitmap = DrawSurfaceComponentOnBitmap();
    }

    public SurfaceComponent(ImageSource source){
        this(source, new Position());
    }

    public ImageSource getImageSource() {
        return imageSource;
    }

    public void setImageSource(ImageSource imageSource) {
        this.imageSource = imageSource;
    }

    public Position getImagePositionOnSurface() {
        return imagePositionOnSurface;
    }

    public void setImagePositionOnSurface(Position position) {
        this.imagePositionOnSurface = position;
    }

    public Bitmap getSurfaceComponentBitmap() {
        return surfaceComponentBitmap;
    }

    public void setSurfaceComponentBitmap(Bitmap surfaceComponentBitmap) {
        this.surfaceComponentBitmap = surfaceComponentBitmap;
    }

    public boolean isEnabled() { return isEnabled; }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void Run() {
        DrawSurfaceComponentOnBitmap();
    }

    public String getSurfaceComponentName() {
        return getImageSource().getSourceName();
    }

    public Bitmap DrawSurfaceComponentOnBitmap() {
        //return imageSource.getImage();
        return Bitmap.createScaledBitmap(imageSource.getImage(),imagePositionOnSurface.getWidth(),imagePositionOnSurface.getHeight(),true);
    }
}
