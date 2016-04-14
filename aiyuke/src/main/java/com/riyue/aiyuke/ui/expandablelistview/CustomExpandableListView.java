package com.riyue.aiyuke.ui.expandablelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;

/**
 * Created by Mr.xu on 2016/4/11.
 */
public class CustomExpandableListView extends ExpandableListView {
    public CustomExpandableListView(Context context) {
        this(context, null);
    }

    public CustomExpandableListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, measureSpec);
    }

    /**
     * 拦截滑动事件
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE){
            return true;
        }

        return super.dispatchTouchEvent(ev);
    }
}
