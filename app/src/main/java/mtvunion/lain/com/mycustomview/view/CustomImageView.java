package mtvunion.lain.com.mycustomview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import mtvunion.lain.com.mycustomview.R;


/**
 * Created by 兰海兵 on 2016/6/1.
 */
public class CustomImageView extends ImageView {
    private String titleText;
    private int titleTextColor;
    private int imageScaleType;
    private Bitmap mImage;
    private int titleTextSize;
    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * 初始化所特有自定义类型
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i <n ; i++) {
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.CustomImageView_titleText1:
                    titleText =array.getString(attr);
                    break;
                case R.styleable.CustomImageView_titleColor1:
                    titleTextColor = array.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.CustomImageView_titleSize1:
                    titleTextSize = array.getInt(attr,18);
                    break;
                case R.styleable.CustomImageView_image:
                    mImage = BitmapFactory.decodeResource(getResources(),array.getResourceId(attr,0));
                    break;
                case R.styleable.CustomImageView_imageScaleType:
                    imageScaleType = array.getIndex(attr);
                    break;
            }
        }
        array.recycle();
        mPaint = new Paint();
        mBound = new Rect();
        mPaint.setTextSize(titleTextSize);
        // 计算了描绘字体需要的范围
        mPaint.getTextBounds(titleText, 0, titleText.length(), mBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width;
        int height;
        //设置宽度
        int sprcMode = MeasureSpec.getMode(widthMeasureSpec);
        int sprcSize = MeasureSpec.getSize(widthMeasureSpec);
        if(sprcMode==MeasureSpec.EXACTLY){
            width = sprcSize;
        }else{
            //由图片决定的宽

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
