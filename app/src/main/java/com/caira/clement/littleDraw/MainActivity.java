package com.caira.clement.littleDraw;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements ColorDialog.OnColorPickedListener {

    ImageButton colorBtn;
    private ColorDialog colorDialog;
    private DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        colorBtn = findViewById(R.id.color_btn);
        colorBtn.setOnClickListener(colorPickerHandler);

        drawingView = findViewById(R.id.drawing);
    }

    View.OnClickListener colorPickerHandler = new View.OnClickListener() {
        public void onClick(View v) {
            FragmentManager fm = MainActivity.this.getSupportFragmentManager();
            colorDialog = new ColorDialog();
            colorDialog.show(fm, null);
        }
    };

    @Override
    public void onColorPicked(int color) {
        colorDialog.dismiss();
        drawingView.changeColor(color);
    }
}
