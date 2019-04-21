package wang.ralf.customview_upgrade;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * @author Ralf
 * DESCRIPTION
 * @date 2018/10/27 下午8:51
 **/
public class GrowUpLayout extends RelativeLayout implements GrowUpView.GrowUpDrawListener {

    private GrowUpView mGrowUpView;
    private Paint textPaint;
    private List<String> urlList;
    private List<String> titleList;
    private int totalLevel;
    private int currentLevel;
    private int mImageWidth;
    private Context mContext;
    private RequestOptions mRequestOptions;

    public GrowUpLayout(Context context) {
        this(context, null);
    }

    public GrowUpLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GrowUpLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();

    }

    private void initView() {

        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_growup_container, this);
        mGrowUpView = rootView.findViewById(R.id.growupview);
        mGrowUpView.setGrowUpDrawListener(this);

        mRequestOptions = new RequestOptions()
                .circleCrop()
                .error(R.mipmap.ic_launcher);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * width 是背景圆的直径
     *
     * @param startX
     * @param startY
     */
    @Override
    public void drawListener(float startX, float startY, int interval, float radius) {

        int imageWidth = mImageWidth > 0 ? mImageWidth : 12;
        if (urlList != null) {
            for (int i = 0; i < urlList.size(); i++) {
                String url = urlList.get(i);
                ImageView imageView = new ImageView(getContext());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageWidth, imageWidth);
                layoutParams.setMargins(interval * i + (int) radius - imageWidth / 2, (int) startY - imageWidth / 2, 0, 0);

                imageView.setLayoutParams(layoutParams);
                addView(imageView);

                Glide.with(this)
                        .load(url)
                        .apply(mRequestOptions)
                        .into(imageView);

            }
        }
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        mGrowUpView.setUrlList(urlList);
        this.urlList = urlList;
    }

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
        mGrowUpView.setTitleList(titleList);
    }

    public int getTotalLevel() {
        return totalLevel;
    }

    public void setTotalLevel(int totalLevel) {
        mGrowUpView.setTotalLevel(totalLevel);
        this.totalLevel = totalLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
        mGrowUpView.setCurrentLevel(currentLevel);
        requestLayout();
    }

    public void setImageViewWidth(int width) {
        this.mImageWidth = AppUtil.dp2px(mContext, width);
    }

}
