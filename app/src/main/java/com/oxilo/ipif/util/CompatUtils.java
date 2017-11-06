package com.oxilo.ipif.util;

import android.content.Context;

/**
 * Created by nikk on 23/7/17.
 */

public class CompatUtils {
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
