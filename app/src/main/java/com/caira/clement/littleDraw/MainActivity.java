package com.caira.clement.littleDraw;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements ColorDialog.OnColorPickedListener {

    private ImageButton saveBtn;
    ImageButton colorBtn;
    private ColorDialog colorDialog;
    private DrawingView drawingView;

    @RequiresApi(api = Build.VERSION_CODES.M)
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
            OutputStream fOut = null;
            Bitmap drawingBM = drawingView.getDrawingCache();
            try {
                if (drawingBM != null) {
                    File mediaDirectory = new File(Environment.getExternalStorageDirectory().getPath()
                            + "/DCIM");

                    mediaDirectory.mkdir();
                    File f = new File(mediaDirectory, UUID.randomUUID().toString() + ".png");
                    fOut = new FileOutputStream(f);

                    BufferedOutputStream bos = new BufferedOutputStream(fOut);

                    drawingBM.compress(Bitmap.CompressFormat.PNG, 100, bos);

                    bos.flush();
                    bos.close();


                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                    savedToast.show();
                } else {

                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "NOPE !", Toast.LENGTH_SHORT);
                    savedToast.show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onColorPicked(int color) {
        colorDialog.dismiss();
        drawingView.changeColor(color);
    }
}
