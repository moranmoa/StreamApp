package com.example.moran_lap.projbitmapv11;

/**
 * Created by Gili on 18/05/2016.
 */
public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
