package mph.trunksku.apps.dexpro.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.ListView;
public class BottomSheetListView extends ListView {

    private boolean mIsBeingDragged = false;
    private float mLastMotionY;

    public BottomSheetListView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomSheetListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                    if (canScrollDown() || canScrollUp()) {
                        final ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                    break;
                }
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float y = event.getRawY();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE: {
                    if (!mIsBeingDragged) {
                        final float deltaY = mLastMotionY - y;
                        mIsBeingDragged = (deltaY > 0 && canScrollDown())
                            || (deltaY < 0 && canScrollUp());

                        if (mIsBeingDragged) {
                            final ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                        } else {
                            final ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(false);
                            }
                            return false;
                        }
                    }
                }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                break;
        }

        mLastMotionY = y;

        return super.onTouchEvent(event);
    }

    public boolean canScrollUp() {
        final int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }

        final int firstPosition = getFirstVisiblePosition();
        final int firstTop = getChildAt(0).getTop();
        return firstPosition > 0 || firstTop < getListPaddingTop();
    }

    public boolean canScrollDown() {
        final int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }

        final int firstPosition = getFirstVisiblePosition();
        final int lastBottom = getChildAt(childCount - 1).getBottom();
        final int lastPosition = firstPosition + childCount;
        return lastPosition < getCount() || lastBottom > getHeight() - getListPaddingBottom();
    }
}
