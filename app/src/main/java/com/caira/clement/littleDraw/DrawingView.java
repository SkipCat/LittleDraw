package com.caira.clement.littleDraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawingView extends View {

    private Path drawPath;
    private Paint drawPaint, canvasPaint;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Paint> paints = new ArrayList<>();

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    public DrawingView(Context context) {
        super(context);
        setupDrawing();
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupDrawing();
    }

    protected void setupDrawing() {
        // Initializing paintbrush
        drawPath = new Path();
        paths.add(drawPath);
        drawPaint = new Paint();
        paints.add(drawPaint);

        drawPaint.setColor(0xFF000000); // 0xFF + hexa color code
        drawPaint.setStrokeWidth(30);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND); // Transforms intersection into round
        drawPaint.setStrokeCap(Paint.Cap.ROUND); // Transforms extremities into round

        canvasPaint = new Paint(Paint.DITHER_FLAG); // Canvas
    }

    public void changeColor(int color) {
        Path newPath = new Path();
        Paint newPaint = new Paint();

        newPaint.setColor(color);
        newPaint.setStrokeWidth(30);
        newPaint.setStyle(Paint.Style.STROKE);
        newPaint.setStrokeJoin(Paint.Join.ROUND); // Transforms intersection into round
        newPaint.setStrokeCap(Paint.Cap.ROUND); // Transforms extremities into round

        paths.add(newPath);
        paints.add(newPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Initializing drawing zone
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);

        for (int i = 0; i < paths.size(); i ++) {
            canvas.drawPath(paths.get(i), paints.get(i));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        Path path = paths.get(paths.size() - 1);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(touchX, touchY);
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }
}
