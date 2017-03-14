
package com.zhoujian.mykeep.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.widget.TextView;
import com.zhoujian.mykeep.R;

public class CircleProgressbar extends TextView
{

    //外部轮廓的颜色
    private int outLineColor = Color.BLACK;

    //外部轮廓的宽度
    private int outLineWidth = 2;

    //内部圆的颜色
    private ColorStateList inCircleColors = ColorStateList.valueOf(Color.TRANSPARENT);

    //中心圆的颜色
    private int circleColor;

    //进度条的颜色
    private int progressLineColor = Color.BLUE;

    //进度条的宽度
    private int progressLineWidth = 8;

    //画笔
    private Paint mPaint = new Paint();

    //进度条的矩形区域
    private RectF mArcRect = new RectF();

    //进度
    private int progress = 100;

    //进度条类型
    private ProgressType mProgressType = ProgressType.COUNT_BACK;

    //进度倒计时时间
    private long timeMillis = 3000;

    //View的显示区域。
    final Rect bounds = new Rect();

    //进度条通知。
    private OnCountdownProgressListener mCountdownProgressListener;
    private int listenerWhat = 0;

    public CircleProgressbar(Context context) {
        this(context, null);
    }

    public CircleProgressbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleProgressbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs);
    }


    private void initialize(Context context, AttributeSet attributeSet) {
        mPaint.setAntiAlias(true);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CircleProgressbar);
        if (typedArray.hasValue(R.styleable.CircleProgressbar_in_circle_color))
            inCircleColors = typedArray.getColorStateList(R.styleable.CircleProgressbar_in_circle_color);
        else
            inCircleColors = ColorStateList.valueOf(Color.TRANSPARENT);
        circleColor = inCircleColors.getColorForState(getDrawableState(), Color.TRANSPARENT);
        typedArray.recycle();
    }


    public void setOutLineColor(@ColorInt int outLineColor) {
        this.outLineColor = outLineColor;
        invalidate();
    }


    public void setOutLineWidth(@ColorInt int outLineWidth) {
        this.outLineWidth = outLineWidth;
        invalidate();
    }


    public void setInCircleColor(@ColorInt int inCircleColor) {
        this.inCircleColors = ColorStateList.valueOf(inCircleColor);
        invalidate();
    }


    private void validateCircleColor() {
        int circleColorTemp = inCircleColors.getColorForState(getDrawableState(), Color.TRANSPARENT);
        if (circleColor != circleColorTemp) {
            circleColor = circleColorTemp;
            invalidate();
        }
    }


    public void setProgressColor(@ColorInt int progressLineColor) {
        this.progressLineColor = progressLineColor;
        invalidate();
    }


    public void setProgressLineWidth(int progressLineWidth) {
        this.progressLineWidth = progressLineWidth;
        invalidate();
    }


    public void setProgress(int progress) {
        this.progress = validateProgress(progress);
        invalidate();
    }


    private int validateProgress(int progress) {
        if (progress > 100)
            progress = 100;
        else if (progress < 0)
            progress = 0;
        return progress;
    }


    public int getProgress() {
        return progress;
    }


    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
        invalidate();
    }


    public long getTimeMillis() {
        return this.timeMillis;
    }


    public void setProgressType(ProgressType progressType) {
        this.mProgressType = progressType;
        resetProgress();
        invalidate();
    }


    private void resetProgress()
    {
        switch (mProgressType)
        {
            case COUNT:
                progress = 0;
                break;
            case COUNT_BACK:
                progress = 100;
                break;
        }
    }

    public ProgressType getProgressType() {
        return mProgressType;
    }


    public void setCountdownProgressListener(int what, OnCountdownProgressListener mCountdownProgressListener) {
        this.listenerWhat = what;
        this.mCountdownProgressListener = mCountdownProgressListener;
    }


    public void start()
    {
        stop();
        post(progressChangeTask);
    }


    public void reStart()
    {
        resetProgress();
        start();
    }


    public void stop() {
        removeCallbacks(progressChangeTask);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取view的边界
        getDrawingRect(bounds);

        int size = bounds.height() > bounds.width() ? bounds.width() : bounds.height();
        float outerRadius = size / 2;

        //画内部背景
        int circleColor = inCircleColors.getColorForState(getDrawableState(), 0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(circleColor);
        canvas.drawCircle(bounds.centerX(), bounds.centerY(), outerRadius - outLineWidth, mPaint);

        //画边框圆
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(outLineWidth);
        mPaint.setColor(outLineColor);
        canvas.drawCircle(bounds.centerX(), bounds.centerY(), outerRadius - outLineWidth / 2, mPaint);

        //画字
        Paint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        float textY = bounds.centerY() - (paint.descent() + paint.ascent()) / 2;
        canvas.drawText(getText().toString(), bounds.centerX(), textY, paint);

        //画进度条
        mPaint.setColor(progressLineColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(progressLineWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        int deleteWidth = progressLineWidth + outLineWidth;
        mArcRect.set(bounds.left + deleteWidth / 2, bounds.top + deleteWidth / 2, bounds.right - deleteWidth / 2, bounds.bottom - deleteWidth / 2);

        canvas.drawArc(mArcRect, 0, 360 * progress / 100, false, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int lineWidth = 4 * (outLineWidth + progressLineWidth);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int size = (width > height ? width : height) + lineWidth;
        setMeasuredDimension(size, size);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        validateCircleColor();
    }


    private Runnable progressChangeTask = new Runnable() {
        @Override
        public void run() {
            removeCallbacks(this);
            switch (mProgressType) {
                case COUNT:
                    progress += 1;
                    break;
                case COUNT_BACK:
                    progress -= 1;
                    break;
            }
            if (progress >= 0 && progress <= 100) {
                if (mCountdownProgressListener != null)
                    mCountdownProgressListener.onProgress(listenerWhat, progress);
                invalidate();
                postDelayed(progressChangeTask, timeMillis / 100);
            } else
                progress = validateProgress(progress);
        }
    };


    public enum ProgressType {
        /**
         * 顺数进度条，从0-100；
         */
        COUNT,

        /**
         * 倒数进度条，从100-0；
         */
        COUNT_BACK;
    }

    public interface OnCountdownProgressListener
    {
        void onProgress(int what, int progress);
    }
}
