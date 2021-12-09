package mph.trunksku.apps.dexpro.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

class PageAdapter extends PagerAdapter {
    private ArrayList<String> title;

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        return viewGroup;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    PageAdapter(ArrayList<String> arrayList) {
        this.title = arrayList;
    }

    public int getCount() {
        return this.title.size();
    }

    public CharSequence getPageTitle(int i) {
        return (CharSequence) this.title.get(i);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}

