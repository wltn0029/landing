package com.example.q.ondraw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Block {
    public int x;
    public int y;
    int width=50;
    int height = 50;
    private Paint paint;
    boolean isFilled ;
    private Rect rect;
    int boundaryColor = Color.BLACK;
    int filledColor;
    public Block (int x, int y, int color){
        this.x = x;
        this.y = y;
        paint =  new Paint();
        filledColor = color;
        paint.setAntiAlias(true);
        paint.setColor(boundaryColor);
        isFilled = false;
        paint.setStyle(Paint.Style.STROKE);
        rect = new Rect();
    }
    public boolean isFilled(){ return isFilled; }
    public void setFilled(boolean filled){ isFilled = filled; }
    public void setPosition(int x,int y){ this.x = x; this.y = y;}
    public void drawBlock(Canvas c){
        if(isFilled){
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(filledColor);
        }
        c.drawRect(x*width+5,y*width+5,x*width+55,y*width+55,paint);
    }

}
