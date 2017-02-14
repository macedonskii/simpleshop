package home.mad.simpleshop.other.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.widget.Checkable;

import home.mad.simpleshop.R;


public class CheckableFloatingActionButton extends FloatingActionButton implements Checkable {

    private OnCheckedChangeListener listener;

    private boolean checked;

    public CheckableFloatingActionButton(Context context) {
        super(context);
    }

    public CheckableFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setChecked(attrs);
    }

    public CheckableFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setChecked(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CheckableFloatingActionButton);
        setChecked(array.getBoolean(R.styleable.CheckableFloatingActionButton_android_checked, false));
        array.recycle();
    }

    @Override
    public boolean isChecked() {
        return isSelected();
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override
    public void setChecked(boolean b) {
        setSelected(b);

    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
        if (listener != null){
            listener.onChecked(this,isChecked());
        }
    }

    public OnCheckedChangeListener getOnCheckListener() {
        return listener;
    }

    public void setOnCheckListener(OnCheckedChangeListener listener) {
        this.listener = listener;
    }

    public interface OnCheckedChangeListener{
        void onChecked(CheckableFloatingActionButton button, boolean isChecked);
    }


}
