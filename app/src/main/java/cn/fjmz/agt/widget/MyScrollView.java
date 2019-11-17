package cn.fjmz.agt.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

public class MyScrollView extends NestedScrollView {

    public interface ScrollViewListener {
        void onScrollChanged(MyScrollView scrollView, int x, int y,
                             int oldX, int oldY);
    }

    private ScrollViewListener scrollViewListener = null;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs,
                        int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldX, oldY);
        }
    }

}

