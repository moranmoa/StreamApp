package com.example.moran_lap.projbitmapv11;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Gili on 15/04/2016.
 */
public class ApplicationContext {

    private static Context mContext;
    private static Activity mActivity;

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        ApplicationContext.mContext = mContext;
    }

    public static Activity getActivity() {
        return mActivity;
    }

    public static void setActivity(Activity mActivity) {
        ApplicationContext.mActivity = mActivity;
    }
}
