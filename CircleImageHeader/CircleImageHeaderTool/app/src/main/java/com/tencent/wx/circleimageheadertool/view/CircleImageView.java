package com.tencent.wx.circleimageheadertool.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by v_zakudong on 2017/9/15.
 */

public class CircleImageView extends ImageView{
    private Paint mPaint;

    public CircleImageView(Context context) {
        this(context,null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable!=null){
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap b = getCreateBitmap(bitmap);
            Rect rectsrc = new Rect(0,0,b.getWidth(),b.getWidth());
            Rect rectDesy = new Rect(0,0,getWidth(),getHeight());
            mPaint.reset();
            canvas.drawBitmap(b,rectsrc,rectDesy,mPaint);
        }else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getCreateBitmap(Bitmap bitmap) {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        final int color = 0xff424242;
        Rect rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        mPaint.setARGB(0,0,0,0);
        mPaint.setColor(color);
        int x = bitmap.getWidth();
        canvas.drawCircle(x/2,x/2,x/2-15,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,rect,rect,mPaint);
        return newBitmap;
    }
}
