package com.caira.clement.littleDraw;

import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements ColorDialog.OnColorPickedListener {

    ImageButton saveBtn;
    ImageButton colorBtn;
    private ColorDialog colorDialog;
    private DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        saveBtn = findViewById(R.id.save_btn);
        colorBtn = findViewById(R.id.color_btn);
        saveBtn.setOnClickListener(saveBtnHandler);
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

    View.OnClickListener saveBtnHandler = new View.OnClickListener() {
        public void onClick(View v) {
            drawingView.setDrawingCacheEnabled(true);
            String imgSaved = MediaStore.Images.Media.insertImage(
                    getContentResolver(), drawingView.getDrawingCache(),
                    UUID.randomUUID().toString()+".png", "drawing");
            if(imgSaved!=null){
                Toast savedToast = Toast.makeText(getApplicationContext(),
                        "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                savedToast.show();
            }
            else{
                Toast unsavedToast = Toast.makeText(getApplicationContext(),
                        "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                unsavedToast.show();
            }
            drawingView.destroyDrawingCache();
        }
    };

    @Override
    public void onColorPicked(int color) {
        colorDialog.dismiss();
        drawingView.changeColor(color);
    }
}
