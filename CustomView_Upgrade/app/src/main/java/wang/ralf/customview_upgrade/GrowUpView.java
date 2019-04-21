package wang.ralf.customview_upgrade;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ralf
 * DESCRIPTION
 * @date 2018/10/27 下午5:01
 **/
public class GrowUpView extends View {

    private Paint bgPaint;
    private Paint proPaint;
    private Paint textPaint;

    private float bgRadius;
    private float proRadius;
    private float startX;
    private float stopX;
    private float bgCenterY;
    private int lineBgWidth;
    private int bgColor;
    private int lineProWidth;
    private int proColor;
    private int textColor;
    private int textPadding;
    private int timePadding;
    private int maxStep;
    private int textSize;

    private List<String> urlList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private Map<String, String> map;

    /**
     * 总的等级，如18级，level/subStep = maxStep（18 / 3 = 6）
     * proStep = currentLevel / subStep
     * 每个间隔之间分成3等分，subInterval= （currentLevel - 1） % subStep
     */
    private int totalLevel = 18;
    private int currentLevel = 1;
    // 当前处于的大格子数
    private int proStep;
    // 每个间隔中的等分数
    private int subStep;
    // 两个大格之间的间隔
    private int interval;

    GrowUpDrawListener mGrowUpDrawListener;

    public GrowUpView(Context context) {
        this(context, null);
    }

    public GrowUpView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GrowUpView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GrowUpView);
        bgRadius = ta.getDimension(R.styleable.GrowUpView_bg_radius, 16);
        proRadius = ta.getDimension(R.styleable.GrowUpView_pro_radius, 16);

        lineBgWidth = (int) ta.getDimension(R.styleable.GrowUpView_bg_width, 3f);

        bgColor = ta.getColor(R.styleable.GrowUpView_bg_color, Color.parseColor("#cdcbcc"));
        lineProWidth = (int) ta.getDimension(R.styleable.GrowUpView_pro_width, 2f);

        proColor = ta.getColor(R.styleable.GrowUpView_pro_color, Color.parseColor("#029dd5"));
        textPadding = (int) ta.getDimension(R.styleable.GrowUpView_text_padding, 8);

        maxStep = ta.getInt(R.styleable.GrowUpView_max_step, 6);
        textSize = (int) ta.getDimension(R.styleable.GrowUpView_textsize, 12);
        proStep = ta.getInt(R.styleable.GrowUpView_pro_step, 1);
        subStep = ta.getInt(R.styleable.GrowUpView_sub_step, 3);
        textColor = ta.getColor(R.styleable.GrowUpView_text_color, Color.parseColor("#ffffff"));

        ta.recycle();
        initView();
    }

    private void initView() {

        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(bgColor);
        bgPaint.setStrokeWidth(lineBgWidth);
        bgPaint.setTextSize(textSize);
        bgPaint.setTextAlign(Paint.Align.CENTER);

        proPaint = new Paint();
        proPaint.setAntiAlias(true);
        proPaint.setStyle(Paint.Style.FILL);
        proPaint.setColor(proColor);
        proPaint.setStrokeWidth(lineProWidth);
        proPaint.setTextSize(textSize);
        proPaint.setTextAlign(Paint.Align.CENTER);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int bgWidth;

        if (widthMode == MeasureSpec.EXACTLY) {
            bgWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        } else {
            bgWidth = AppUtil.dp2px(getContext(), 300);
        }

        int bgHeight;
        if (heightMode == MeasureSpec.EXACTLY) {
            bgHeight = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        } else {
            bgHeight = AppUtil.dp2px(getContext(), 60);
        }

        float left = getPaddingLeft() + bgRadius;
        stopX = bgWidth - bgRadius;
        startX = left;
        bgCenterY = bgHeight / 2;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        interval = (int) ((stopX - startX) / (maxStep - 1));
        drawBg(canvas);
        drawProgress(canvas);
        loadBitmap();
        drawText(canvas);
    }

    private void drawBg(Canvas canvas) {

        canvas.drawLine(startX, bgCenterY, stopX, bgCenterY, bgPaint);
        for (int i = 0; i < maxStep; i++) {
            float cx = startX + i * interval;
            canvas.drawCircle(cx, bgCenterY, bgRadius, bgPaint);
        }
    }

    private void drawProgress(Canvas canvas) {

        int subInterval = interval / subStep;
        // 大的间隔 + 小的间隔
        int linePro = (currentLevel - 1) / subStep * interval + (currentLevel - 1) % subStep * subInterval;
        canvas.drawLine(startX, bgCenterY, startX + linePro, bgCenterY, proPaint);

        // 计算进度，稍微复杂，123 - 1；456 - 2；以此类推
        if (currentLevel % subStep == 0) {
            proStep = currentLevel / subStep;
        } else {
            proStep = currentLevel / subStep + 1;
        }
        for (int i = 0; i < proStep; i++) {
            canvas.drawCircle(startX + i * interval, bgCenterY, proRadius, proPaint);
        }
    }

    private void loadBitmap() {
        if (mGrowUpDrawListener != null) {
            // 传递起始圆心坐标过去
            mGrowUpDrawListener.drawListener(startX, bgCenterY, interval, bgRadius);
        }
    }

    private void drawText(Canvas canvas) {

        for (int i = 0; i < maxStep; i++) {

            // 已完成的部分
            if (i < proStep) {
                setTextPaintColor(i);
                if (null != titleList && i < titleList.size()) {
                    canvas.drawText(titleList.get(i), startX + (i * interval), bgCenterY + textPadding, textPaint);
                }
            }
            // 未完成部分
            else {
                if (null != titleList && i < titleList.size()) {
                    String title = titleList.get(i);
                    if (null == title) continue;
                    setTextDefaultPaintColor();
                    canvas.drawText(title, startX + (i * interval), bgCenterY + textPadding, textPaint);
                }
            }

        }
    }

    private void setTextDefaultPaintColor() {
        textPaint.setColor(textColor);
    }

    private void setTextPaintColor(int i) {
        if (titleList == null || map == null) return;
        String title = titleList.get(i);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (title.contains(entry.getKey())) {
                textPaint.setColor(Color.parseColor(entry.getValue()));
                return;
            } else {
                textPaint.setColor(textColor);
            }
        }
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public int getTotalLevel() {
        return totalLevel;
    }

    public void setTotalLevel(int totalLevel) {
        this.totalLevel = totalLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
        requestLayout();
    }

    public void setGrowUpDrawListener(GrowUpDrawListener growUpDrawListener) {
        this.mGrowUpDrawListener = growUpDrawListener;
    }

    public interface GrowUpDrawListener {
        /**
         * 加载图片
         *
         * @param startX   起始坐标 X
         * @param startY   起始坐标 Y
         * @param interval 间隔
         * @param radius   背景色半径
         */
        void drawListener(float startX, float startY, int interval, float radius);
    }
}
