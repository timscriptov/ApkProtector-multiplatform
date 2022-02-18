package com.admin.ffffffffffffffffffffffff;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            LinearLayout mainLayout = new LinearLayout(this);
            mainLayout.setOrientation(LinearLayout.VERTICAL);
            mainLayout.setGravity(Gravity.CENTER);
            mainLayout.setPadding(40, 0, 40, 0);
            mainLayout.setLayoutParams(layoutParams);

            TextView b = new TextView(this);
            b.setText("TEST");
            mainLayout.addView(b);

            setContentView(mainLayout);
        }
}