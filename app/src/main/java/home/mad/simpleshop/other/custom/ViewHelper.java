package home.mad.simpleshop.other.custom;

import android.content.Context;
import android.util.DisplayMetrics;


import home.mad.simpleshop.R;

/**
 * Created by mad on 08.12.2016.
 */

public class ViewHelper {

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int noOfColumns = (int) (displayMetrics.widthPixels / context.getResources().getDimension(R.dimen.column_width_grid_view));
        return Math.max(1, noOfColumns);
    }
}
