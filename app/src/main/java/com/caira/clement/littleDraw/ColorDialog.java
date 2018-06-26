package com.caira.clement.littleDraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ColorDialog extends DialogFragment { // android.app or android.app.support.v4 ?

    ImageButton blackBtn;
    ImageButton blueBtn;
    ImageButton pinkBtn;

    public interface OnColorChangedListener {
        void colorChanged(int color);
    }

    private OnColorChangedListener colorListener;
    private int initialColor;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        System.out.println("in onCreateDialog");
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick a color");
        builder.setView(R.layout.color_dialog);

        return builder.create();
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        blackBtn = findViewById(R.id.black_btn);
        blackBtn.setOnClickListener(colorBtnHandler);
        blueBtn = findViewById(R.id.blue_btn);
        blueBtn.setOnClickListener(colorBtnHandler);
        pinkBtn = findViewById(R.id.pink_btn);
        pinkBtn.setOnClickListener(colorBtnHandler);
    }
    */

    /*
    View.OnClickListener colorBtnHandler = new View.OnClickListener() {
        public void onClick(View v) {
            System.out.println("test");
        }
    };
    */

}
