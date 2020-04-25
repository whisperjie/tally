package com.whisper.tally.util.pie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/*
果然还是要自己写过才知道啊;
神他妹调用接口就行了;
要先画一个矩形;
在矩形里面画圆

 */
public class PieView extends View {
    public int[] mColor = {0xFF1493, 0x20B2AA};
    public float mStartAngle = 0;
    public ArrayList<PieData> mData;
    public int mWidth, mHeight;
    public Paint mPaint = new Paint();
    float sumAngle=0f;

    public PieView(Context context) {
        //super(context);
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mData) {
            return;
        }
        float currentStartAnggle = mStartAngle;//起始角度
        canvas.translate(mWidth / 2, mHeight / 2); //原点移动到中心
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rect = new RectF(-r, -r, r, r);
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rect, currentStartAnggle, pie.getAngle(), true, mPaint);
            currentStartAnggle += pie.getAngle();
        }
    }

    public void setmStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
    }

    public void setmData(ArrayList<PieData> mData) {
        this.mData = mData;
        initDate(this.mData);
        invalidate();
    }

    private void initDate(ArrayList<PieData> mData) {
        Log.d("MYTAG",mData.toString());
        if (null == mData || mData.size() == 0) {
            return;
        }
        float sumValue = 0.0f;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie=mData.get(i);
            sumValue+=pie.getValue(); //计算数值和
            int j=i%mColor.length;
            pie.setColor(mColor[j]); //设置颜色
        }
        Log.d("MYTAG>>sum", String.valueOf(sumValue));
        for (int i=0;i<mData.size();i++){
            PieData pie=mData.get(i);
            float percentage=pie.getValue()/sumValue;
            float angle=percentage*360; //计算角度
            pie.setPercentage(percentage);
            pie.setAngle(angle);
            Log.i("MYTAG>>percentage",">>>>>>>>>>>PIE"+pie.getPercentage());
            sumAngle+=angle;
            Log.i("MYTAG>>angle",">>>>>>>>>>>PIE"+pie.getAngle());
        }
        Log.d("MYTAG",mData.toString());
    }

}
