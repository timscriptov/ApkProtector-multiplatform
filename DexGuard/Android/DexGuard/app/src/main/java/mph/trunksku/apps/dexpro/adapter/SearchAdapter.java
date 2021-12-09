package mph.trunksku.apps.dexpro.adapter;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import mph.trunksku.apps.dexpro.model.IgnoredClass;

public class SearchAdapter extends IgnoredClassAdapter {
    private List<IgnoredClass> backuplist;
    Map<String, TextHighlight> highlight = new HashMap();
    private SearchTask mSearchTask;

    public class Highlight {
        public int end = -1;
        public int start = -1;

    }

    class SearchTask extends AsyncTask<String, Void, List<IgnoredClass>> {
        boolean cancel;
        private final SearchAdapter sak;

        


        void cancel() {
            this.cancel = true;
            cancel(true);
        }

        @Override
        protected List<IgnoredClass> doInBackground(String[] strArr) {
            String str = strArr[0];
            if (str == null || str.trim().length() == 0) {
                return sak.backuplist;
            }
            String toLowerCase = str.toLowerCase(Locale.ENGLISH);
            List<IgnoredClass> arrayList = new ArrayList();
            for (int i = 0; i < sak.backuplist.size(); i++) {
                if (this.cancel) {
                    return arrayList;
                }
                IgnoredClass permission = (IgnoredClass) sak.backuplist.get(i);
                String toLowerCase2 = permission.getLabel().toLowerCase(Locale.ENGLISH);
                String toLowerCase3 = permission.getName().toLowerCase(Locale.ENGLISH);
                int indexOf = toLowerCase2.indexOf(toLowerCase);
                int indexOf2 = toLowerCase3.indexOf(toLowerCase);
                if (indexOf >= 0 || indexOf2 >= 0) {
                    arrayList.add(permission);
                    TextHighlight textHighlight = new TextHighlight();
                    textHighlight.title.start = indexOf;
                    textHighlight.title.end = indexOf + toLowerCase.length();
                    textHighlight.subtitle.start = indexOf2;
                    textHighlight.subtitle.end = indexOf2 + toLowerCase.length();
                    sak.highlight.put(permission.getName(), textHighlight);
                }
            }
            return arrayList;
        }

        @Override
        protected void onPostExecute(List<IgnoredClass> list) {
            super.onPostExecute(list);
            if (!this.cancel) {
                sak.setCurrentList(list);
                sak.notifyDataSetChanged();
            }
        }

        public SearchTask(SearchAdapter searchAdapter) {
            sak = searchAdapter;
        }
    }

    public class TextHighlight {
         public Highlight subtitle = new Highlight();

        public Highlight title = new Highlight();

        


    }

    public SearchAdapter(Context context, List<IgnoredClass> list) {
        super(context, list);
        this.backuplist = list;
    }

    public void bind(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
                
                @Override
                public void afterTextChanged(Editable editable) {
                }

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    search(charSequence.toString());
                }
            });
    }

    public void search(String str) {
        this.highlight.clear();
        if (this.mSearchTask != null) {
            this.mSearchTask.cancel();
        }
        this.mSearchTask = new SearchTask(this);
        this.mSearchTask.execute(new String[]{str});
    }

    @Override
    public CharSequence getItemTitle(int i) {
        IgnoredClass item = getItem(i);
        TextHighlight textHighlight = (TextHighlight) this.highlight.get(item.getName());
        if (textHighlight == null) {
            return item.getLabel();
        }
        return makeHighlightText(item.getLabel(), textHighlight.title.start, textHighlight.title.end);
    }

    @Override
    public CharSequence getItemSubTitle(int i) {
        String name = getItem(i).getName();
        TextHighlight textHighlight = (TextHighlight) this.highlight.get(name);
        if (textHighlight == null) {
            return name;
        }
        return makeHighlightText(name, textHighlight.subtitle.start, textHighlight.subtitle.end);
    }

    private SpannableStringBuilder makeHighlightText(String str, int i, int i2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        SpannableString spannableString = new SpannableString(str);
        if (i >= 0) {
            BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(getColorAccent(this.context));
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(-1);
            spannableString.setSpan(backgroundColorSpan, i, i2, 33);
            spannableString.setSpan(foregroundColorSpan, i, i2, 33);
        }
        spannableStringBuilder.append(spannableString);
        return spannableStringBuilder;
    }

    public static int getColorAccent(Context context) {
        if (VERSION.SDK_INT < 21) {
            return 0;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843829, typedValue, true);
        return typedValue.data;
    }
}

