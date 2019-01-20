package www.ralf.com.drawabledemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import www.ralf.com.drawabledemo.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ImageTextView
 * @email -
 * @date 2018/12/25 下午1:17
 **/
public class ImageTextView extends LinearLayout {

    /**
     * 图片和文字方向 1 2 3 4 下 右 左 上
     */
    private static final int[] DIRECTIONS = {1, 2, 3, 4};

    private float mTextSize;
    private float mImageSize;
    private float mGap;
    private int mTextColor;
    private boolean isBold;
    private boolean isCircle;
    private int mDirection;

    private Context mContext;
    private ImageView mImageView;
    private TextView mTextView;

    public ImageTextView(Context context) {
        this(context, null);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageTextView, 0, 0);
        mImageSize = typedArray.getDimension(R.styleable.ImageTextView_imageSize, 30);
        mTextSize = typedArray.getDimension(R.styleable.ImageTextView_textSize, 30);
        mGap = typedArray.getDimension(R.styleable.ImageTextView_gap, 30);
        mTextColor = typedArray.getColor(R.styleable.ImageTextView_textColor, Color.BLACK);
        isBold = typedArray.getBoolean(R.styleable.ImageTextView_isBold, false);
        isCircle = typedArray.getBoolean(R.styleable.ImageTextView_isCircle, true);
        mDirection = typedArray.getInteger(R.styleable.ImageTextView_direction, DIRECTIONS[0]);
        typedArray.recycle();

        mTextView = new TextView(mContext);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setPadding(1, 1, 1, 1);
        mTextView.setTextColor(mTextColor);
        mTextView.setTextSize(10);

        mImageView = new ImageView(mContext);

        mTextView.setText("HelloWorld");
        mImageView.setImageResource(R.mipmap.ic_launcher_round);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(160, 160);
        mImageView.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams textViewLayoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTextView.setLayoutParams(textViewLayoutParams);

        switch (mDirection) {
            case 1:
                setOrientation(LinearLayout.VERTICAL);
                addView(mImageView);
                addView(mTextView);
                break;
            case 2:
                setOrientation(LinearLayout.HORIZONTAL);
                addView(mImageView);
                addView(mTextView);
                break;
            case 3:
                setOrientation(LinearLayout.HORIZONTAL);
                addView(mTextView);
                addView(mImageView);
                break;
            case 4:
                setOrientation(LinearLayout.VERTICAL);
                addView(mTextView);
                addView(mImageView);
                break;
            default:
                break;
        }
    }

    public void setData(String title, int res) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        mTextView.setText(title);
        mImageView.setImageResource(res);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth;
        int measureHight;
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            measureWidth = mImageView.getMeasuredWidth() + mTextView.getMeasuredWidth();
            measureHight = mImageView.getMeasuredHeight() + mTextView.getMeasuredHeight();
            setMeasuredDimension(measureWidth, measureHight + (int) mGap);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            measureWidth = mImageView.getMeasuredWidth() + mTextView.getMeasuredWidth();
            setMeasuredDimension(measureWidth, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            measureHight = mImageView.getMeasuredHeight() + mTextView.getMeasuredHeight();
            setMeasuredDimension(widthSize, measureHight + (int) mGap);
        } else {
            setMeasuredDimension(widthSize, heightSize);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        // ImageView
        int p = getMeasuredWidth();
        int c = mImageView.getMeasuredWidth();
        int l1 = p / 2 - c / 2;
        int r1 = p / 2 + c / 2;
        mImageView.layout(l1, 0, r1, mImageView.getMeasuredHeight());
        // TextView
        int c1 = mTextView.getMeasuredWidth();
        int l2 = p / 2 - c1 / 2;
        int r2 = p / 2 + c1 / 2;
        mTextView.layout(l2, c + (int) mGap, r2, mTextView.getMeasuredHeight() + c + (int) mGap);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        mTextView.setText("HelloWorld");
        mImageView.setImageResource(R.mipmap.ic_launcher_round);
        // TODO 不会走此方法
    }
}
