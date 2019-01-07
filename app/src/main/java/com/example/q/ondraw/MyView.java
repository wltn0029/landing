package com.example.q.ondraw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.q.ondraw.MainActivity.*;

public class MyView extends View {
    Boolean isTouch = false;
    Path path;
    Paint m_Paint;
    int userColor;
    int positionX=0;
    int positionY=0;
    Context context;
    ArrayList<Block>Board = new ArrayList<Block>();
    //---------------------------------------------------------------------------------------------
    //constructor
    //---------------------------------------------------------------------------------------------

    public MyView(Context mcontext){
        super(mcontext);
        context =mcontext;
        initMyView();
    }

    public MyView(Context mcontext,AttributeSet attrs){
        super(mcontext,attrs);
        context = mcontext;
        initMyView();
    }
    public MyView(Context context,AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
        initMyView();
    }
    //---------------------------------------------------------------------------------------------
    //override
    //---------------------------------------------------------------------------------------------
    public void initMyView(){
        m_Paint = new Paint();
        m_Paint.setAntiAlias(true);
        m_Paint.setDither(true);
        m_Paint.setColor(0xFFFF0000);
        m_Paint.setStyle(Paint.Style.STROKE);
        m_Paint.setStrokeJoin(Paint.Join.ROUND);
        m_Paint.setStrokeCap(Paint.Cap.ROUND);
        m_Paint.setStrokeWidth(4);
        for(int i=0;i<23;i++)
            for(int j=0;j<23;j++)
                Board.add(new Block(i,j,userColor));
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //set rectangle size as 50
        int leftStart = 5;
        int topStart = 5;
        for(int i=0;i<23;i++) {
            for (int j = 0; j < 23; j++) {
                Board.get(i + 23 * j).drawBlock(canvas);
            }
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float xPos = event.getX();
        float yPos = event.getY();
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            //up
            if(yPos<500&&(((xPos>yPos) &&(xPos<500))||((xPos+yPos<1000)&&(xPos>500)))){
                Toast.makeText(context,"up",Toast.LENGTH_SHORT).show();
                if(positionY>0){
                    positionY+=1;
                }
            }
            //down
            else if(yPos>500&&(((xPos<yPos)&&(xPos>500))||((xPos+yPos>1000)&&(xPos<500)))){
                Toast.makeText(context,"down",Toast.LENGTH_SHORT).show();
                if(positionY<1000){
                    positionY+=1;
                }
            }
            //left
            else if(xPos<500&&(((yPos<500)&&(xPos<yPos))||((yPos>500)&&(xPos+yPos<1000)))){
                Toast.makeText(context,"left",Toast.LENGTH_SHORT).show();
                if(positionX>0){
                    positionX-=1;
                }
            }
            //right
            else if((xPos>500)&&((xPos>yPos)&&(yPos>500)||((xPos+yPos>1000)&&(yPos<500)))){
                Toast.makeText(context,"right",Toast.LENGTH_SHORT).show();
                if(positionX<1000){
                    positionX+=1;
                }
            }
        }
        Board.get(positionX+23*positionY).setFilled(true);
        invalidate();
        return true;
    }

    public void setUserColor(int mColor){userColor = mColor;}
}
