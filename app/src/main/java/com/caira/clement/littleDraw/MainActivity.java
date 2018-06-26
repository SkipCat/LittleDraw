package com.caira.clement.littleDraw;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton colorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        colorBtn = findViewById(R.id.color_btn);
        colorBtn.setOnClickListener(colorPickerHandler);
    }

    View.OnClickListener colorPickerHandler = new View.OnClickListener() {
        public void onClick(View v) {
            FragmentManager fm = MainActivity.this.getSupportFragmentManager();
            ColorDialog colorDialog = new ColorDialog();
            colorDialog.show(fm, null);
        }
    };

}
