package wang.ralf.customview_startlevel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Ralf
 * DESCRIPTION
 * @name StarLevelView
 * @date 2018/10/26 上午10:29
 **/
public class StarLevelView extends View {

    private Context context;
    private int viewWidth;
    private int viewHeight;
    private Bitmap starBitmap;
    private Paint bitmapPaint;
    private int bitMapHeight;

    private int level;
    private int drawableResId;

    public StarLevelView(Context context) {
        this(context, null);
    }

    public StarLevelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarLevelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        // 获取自定义属性样式列表
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StarLevelView);

        level = typedArray.getInt(R.styleable.StarLevelView_level, 1);
        bitMapHeight = typedArray.getInt(R.styleable.StarLevelView_drawable_height, 20);
        drawableResId = typedArray.getResourceId(R.styleable.StarLevelView_drawable, R.drawable.level_star);

        starBitmap = BitmapFactory.decodeResource(context.getResources(), drawableResId);
        bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        starBitmap = setImgSize(starBitmap, Util.dp2px(context, bitMapHeight), Util.dp2px(context, bitMapHeight));

        typedArray.recycle();
    }

    public Bitmap setImgSize(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 获取宽度和高度的测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        switch (widthMode) {
            // 如果使用者没有明确指定View的尺寸，那么我们就给它设置一个默认值
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                // 默认宽度（图标宽度 + 间隔）* 数量 + 宽度/2 + paddingLeft + paddingRight
                viewWidth = starBitmap.getWidth() * level + starBitmap.getWidth() / 3 * (level - 1)
                        + getPaddingStart() + getPaddingEnd();
                break;
            // 如果用户明确指定了尺寸，就按照用户指定的来
            case MeasureSpec.EXACTLY:
                viewWidth = MeasureSpec.getSize(widthMeasureSpec);
                break;
            default:
        }
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                // View高度默认设置为图片高度的两倍，如果涉及到换行，这里还需要做动态计算
                viewHeight = starBitmap.getHeight() + getPaddingTop() + getPaddingBottom();
                break;
            case MeasureSpec.EXACTLY:
                viewHeight = MeasureSpec.getSize(heightMeasureSpec);
                break;
            default:
        }
        // 把测量出来的宽高尺寸设置到view中去
        setMeasuredDimension(viewWidth, viewHeight);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // 图片之间的横向间隔
        int bitmapPadding = starBitmap.getWidth() / 3;
        int left = getPaddingLeft();
        int top = getPaddingTop();
        for (int i = 0; i < level; i++) {
            // 绘制星星图标，（横坐标为图片宽度+相邻两张图片的间隔）*i+整体左边的margin值，纵坐标为view的高度/2-整体的margin值
            canvas.drawBitmap(starBitmap, (starBitmap.getWidth() + bitmapPadding) * i + left, top, bitmapPaint);
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        requestLayout();
    }

    public int getDrawableResId() {
        return drawableResId;
    }

    public void setDrawableResId(int drawableResId) {
        this.drawableResId = drawableResId;
        requestLayout();
    }
}