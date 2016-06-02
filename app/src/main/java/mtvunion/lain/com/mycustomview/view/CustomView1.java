package mtvunion.lain.com.mycustomview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import mtvunion.lain.com.mycustomview.R;

/**
 * Created by 兰海兵 on 2016/5/29.
 */
public class CustomView1 extends View {
    /**
     * 文本
     */
    private String mTitleText;
    /**
     * 文本颜色
     */
    private int mTextColor;
    /**
     * 字体大小
     */
    private int mTextSize;
    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CustomView1(Context context) {
        super(context, null);
    }

    public CustomView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 一般选择复写三个参数的构造方法
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CustomView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView1, defStyleAttr, 0);
        int n = array.getIndexCount();// n 为
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.CustomView1_titleText:
                    mTitleText = array.getString(attr);
                    break;
                case R.styleable.CustomView1_titleColor:
                    /**
                     * 获取颜色 第二个参数为默认颜色，这里默认为蓝色
                     */
                    mTextColor = array.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.CustomView1_titleSize:
                    /**
                     *默认设置为16sp，TypeValue也可以把sp转化为px
                     */
//                                        mTextSize = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
//                                                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    mTextSize = array.getDimensionPixelSize(attr, 16);
                    break;
            }
        }
        array.recycle();
        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mBound = new Rect();
        //参数类型String text, int start, int end, Rect bounds
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
    }

    /**
     * 测量控件的宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec 重写之前先了解MeasureSpec的specMode,一共三种类型：
     *                          EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
     *                          AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
     *                          UNSPECIFIED：表示子布局想要多大就多大，很少使用
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        /**
         * EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
         *AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
         * UNSPECIFIED：表示子布局想要多大就多大，很少使用
         */
        if (widthMode == MeasureSpec.EXACTLY) {

            width = widthSize;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}
