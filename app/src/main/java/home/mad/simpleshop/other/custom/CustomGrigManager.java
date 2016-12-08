package home.mad.simpleshop.other.custom;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by mad on 08.12.2016.
 */

public class CustomGrigManager extends GridLayoutManager {
    String TAG = getClass().getSimpleName();

    public CustomGrigManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomGrigManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public CustomGrigManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

//    @Override
//    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
//        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
//    }

//    @Override
//    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        super.onLayoutChildren(recycler, state);
//    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        Log.d(TAG, "onMeasure() called with: recycler = [" + recycler + "], state = [" + state + "], widthSpec = [" + widthSpec + "], heightSpec = [" + heightSpec + "]");
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }
}
