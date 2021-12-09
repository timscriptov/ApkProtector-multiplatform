package mph.trunksku.apps.dexpro.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

public class VPDrawerLayout extends DrawerLayout implements DrawerLayout.DrawerListener, ViewPager.OnPageChangeListener, OnTouchListener {
    private boolean closeIng = true;
    private ViewPager viewPager;

    public VPDrawerLayout(Context context) {
        super(context);
        block$1020();
    }

    public VPDrawerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        block$1020();
    }

    public VPDrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        block$1020();
    }

    private void block$1020() {
        init();
    }

    private ViewPager findViewPager(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewPager) {
                return (ViewPager) childAt;
            }
            if (childAt instanceof ViewGroup) {
                ViewPager findViewPager = findViewPager((ViewGroup) childAt);
                if (findViewPager != null) {
                    return findViewPager;
                }
            }
        }
        return (ViewPager) null;
    }

    private void init() {
        addDrawerListener(this);
        setOnTouchListener(this);
    }

    @Override
    public void onDrawerClosed(View view) {
        setDrawerLockMode(0);
    }

    @Override
    public void onDrawerOpened(View view) {
        setDrawerLockMode(2);
        if (this.viewPager != null) {
            onPageSelected(this.viewPager.getCurrentItem());
        }
    }

    @Override
    public void onDrawerSlide(View view, float f) {
    }

    @Override
    public void onDrawerStateChanged(int i) {
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewPager findViewPager = findViewPager(this);
        if (findViewPager != null) {
            setViewPager(findViewPager);
        }
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !isDrawerOpen(8388611)) {
            return super.onKeyDown(i, keyEvent);
        }
        closeDrawers();
        return true;
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    @Override
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override
    public void onPageSelected(int i) {
        if (i == this.viewPager.getAdapter().getCount() - 1) {
            setDrawerLockMode(0);
        } else if (getDrawerLockMode(8388611) == 0) {
            setDrawerLockMode(2);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.closeIng = true;
                break;
            case 1:
                if (this.closeIng) {
                    closeDrawers();
                    break;
                }
                break;
            case 2:
                this.closeIng = false;
                break;
        }
        return false;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
    }
}

