package com.caira.clement.littleDraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

public class ColorDialog extends DialogFragment implements View.OnClickListener {

    private ImageButton blackBtn;
    private ImageButton blueBtn;
    private ImageButton pinkBtn;

    private OnColorPickedListener listener;

    public interface OnColorPickedListener {
        void onColorPicked(int color);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.black_btn:
                listener.onColorPicked(Color.BLACK);
                break;
            case R.id.blue_btn:
                listener.onColorPicked(Color.BLUE);
                break;
            case R.id.pink_btn:
                listener.onColorPicked(Color.MAGENTA);
                break;
            default:
                listener.onColorPicked(Color.BLACK);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (OnColorPickedListener) getActivity();
    }

    @Override
    public void onDetach() {
        this.listener = null;
        super.onDetach();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick a color");

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.color_dialog, null, false);
        builder.setView(view);

        blackBtn = view.findViewById(R.id.black_btn);
        blackBtn.setOnClickListener(this);

        blueBtn = view.findViewById(R.id.blue_btn);
        blueBtn.setOnClickListener(this);

        pinkBtn = view.findViewById(R.id.pink_btn);
        pinkBtn.setOnClickListener(this);

        return builder.create();
    }

}
