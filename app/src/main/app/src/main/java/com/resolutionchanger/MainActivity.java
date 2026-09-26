package com.resolutionchanger;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("Resolution Changer для Standoff 2");
        title.setTextSize(20);
        layout.addView(title);

        String[] resolutions = {"16:9", "16:10", "4:3", "18:9", "21:9"};
        int[][] sizes = {{1920,1080},{1920,1200},{1440,1080},{2160,1080},{2520,1080}};

        for (int i = 0; i < resolutions.length; i++) {
            final int[] size = sizes[i];
            Button btn = new Button(this);
            btn.setText(resolutions[i] + " (" + size[0] + "x" + size[1] + ")");
            btn.setOnClickListener(v -> {
                try {
                    Runtime.getRuntime().exec(new String[]{
                        "su", "-c",
                        "wm size " + size[0] + "x" + size[1]
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            layout.addView(btn);
        }

        Button reset = new Button(this);
        reset.setText("Сбросить разрешение");
        reset.setOnClickListener(v -> {
            try {
                Runtime.getRuntime().exec(new String[]{"su", "-c", "wm size reset"});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        layout.addView(reset);

        setContentView(layout);
    }
}
