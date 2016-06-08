package com.example.moran_lap.projbitmapv11;

/**
 * Created by Gili on 08/04/2016.
 */
public class Position {

    private int xStart;
    private int xEnd;
    private int yStart;
    private int yEnd;

    public Position(){

        xStart = 0;
        xEnd = 600;
        yStart = 0;
        yEnd = 600;
    }

    public Position(int xStart, int xEnd, int yStart, int yEnd){

        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
    }

    public int getHeight() { return yEnd - yStart; }

    public int getWidth() { return xEnd - xStart; }

    public int getyEnd() {
        return yEnd;
    }

    public void setyEnd(int yEnd) {
        this.yEnd = yEnd;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
    }

    public int getxEnd() {
        return xEnd;
    }

    public void setxEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
    }
}
