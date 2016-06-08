package com.example.moran_lap.projbitmapv11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Gili on 28/05/2016.
 */
public class RefreshReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent background = new Intent(context, Composer.class);
        context.startService(background);
    }
}
