package com.brl.snappinglistviewapp;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Custom implementation of Android ListView that is able to snap to closest row at the end of the scrolling movement.
 * When scrolling stops, the list computes a smooth scroll to show always the entire row.
 * <p/>
 * You can disable this feature and use this SnappingListView like any other ListView (default is snapping enabled).<br/>
 * You can set the duration in millis of the snapping movement (default is 300 millis).<br/>
 * You can also enable or disable scrolling as needed (default is scrolling enabled).
 * <p/>
 *
 * @author Lorenzo Barbagli
 * @see <a href="http://www.lorenzobarbagli.net">www.lorenzobarbagli.net</a>
 */
public class SnappingListView extends ListView {

    private boolean scrollable = true;
    private boolean scrollingUp = false;
    private boolean scrollingDown = false;
    private boolean snapping = true;
    private int firstVisibleItem;
    private int snappingSpeed = 300;

    public SnappingListView(Context context) {
        super(context);
        setOnScrollListener(snappingScrollListener);
    }

    public SnappingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnScrollListener(snappingScrollListener);

    }

    public SnappingListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnScrollListener(snappingScrollListener);
    }

    /**
     * Custom scroll listener with compute the snapping correctly
     */
    private OnScrollListener snappingScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(final AbsListView listView, int scrollState) {
            if (snapping)
                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        if (scrollingUp) {
                            listView.post(new Runnable() {
                                public void run() {
                                    View child = listView.getChildAt(0);
                                    Rect r = new Rect(0, 0, child.getWidth(), child.getHeight());
                                    double height = child.getHeight() * 1.0;

                                    listView.getChildVisibleRect(child, r, null);

                                    int dpDistance = Math.abs(r.height());
                                    double minusDistance = dpDistance - height;
                                    if (Math.abs(r.height()) < height / 2) {
                                        listView.smoothScrollBy(dpDistance, snappingSpeed);
                                    } else {
                                        listView.smoothScrollBy((int) minusDistance, snappingSpeed);
                                    }
                                    scrollingUp = false;
                                }
                            });
                        }
                        if (scrollingDown) {
                            listView.post(new Runnable() {
                                public void run() {
                                    View child = listView.getChildAt(0);
                                    Rect r = new Rect(0, 0, child.getWidth(), child.getHeight());
                                    double height = child.getHeight() * 1.0;

                                    listView.getChildVisibleRect(child, r, null);

                                    int dpDistance = Math.abs(r.height());
                                    double minusDistance = dpDistance - height;
                                    if (Math.abs(r.height()) < height / 2) {
                                        listView.smoothScrollBy(dpDistance, snappingSpeed);
                                    } else {
                                        listView.smoothScrollBy((int) minusDistance, snappingSpeed);
                                    }
                                    scrollingDown = false;
                                }
                            });
                        }
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        break;
                }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisItem, int visItemCount, int totItemCount) {
            if (snapping) {
                if (firstVisibleItem < firstVisItem)
                    scrollingDown = true;

                if (firstVisibleItem > firstVisItem)
                    scrollingUp = true;

                firstVisibleItem = firstVisItem;
            }
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (scrollable)
            return super.dispatchTouchEvent(ev);
        return ev.getAction() == MotionEvent.ACTION_MOVE || super.dispatchTouchEvent(ev);
    }

    /**
     * Set the snapping enabled or disabled. If <b>snapping</b> is true then the list will snap to closest row so that the entire row will be visible.
     * You can enable/disable snapping even when SnappingListView is already instantiated.
     *
     * @param snapping Boolean value to enable/disable snapping.
     */
    public void setSnappingEnabled(boolean snapping) {
        this.snapping = snapping;
    }

    /**
     * Returns if snapping is enabled or not. If snapping is enabled then the list will snap to closest row so that the entire row will be visible.
     * Default is true.
     *
     * @return True if snapping is enabled, false otherwise
     */
    public boolean isSnappingEnabled() {
        return snapping;
    }

    /**
     * Set the scrolling enabled or disable. If scrolling is enabled then the list will behave as a normal ListView, otherwise it will not react to touch events of type MotionEvent.ACTION_MOVE
     *
     * @param scrollable Boolean value to enable/disable scrolling.
     */
    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    /**
     * Returns if scrolling is enabled or not. If scrolling is enabled then the list will behave as a normal ListView, otherwise it will not react to touch events of type MotionEvent.ACTION_MOVE
     * Default is true.
     *
     * @return True if scrolling is enabled, false otherwise
     */
    public boolean isScrollable() {
        return scrollable;
    }


    /**
     * Set snapping speed in millis.
     *
     * @param snappingSpeed The speed (milliseconds) of smooth scrolling to snapping to closest row.
     */
    public void setSnappingSpeed(int snappingSpeed) {
        this.snappingSpeed = snappingSpeed;
    }

    /**
     * Get snapping speed in millis. Default is 300 millis.
     *
     * @return The speed (milliseconds) of smooth scrolling to snapping to closest row.
     */
    public int getSnappingSpeed() {
        return snappingSpeed;
    }
}
