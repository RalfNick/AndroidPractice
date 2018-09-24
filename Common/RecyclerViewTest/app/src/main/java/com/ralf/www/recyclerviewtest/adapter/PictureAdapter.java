package com.ralf.www.recyclerviewtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ralf.www.recyclerviewtest.R;
import com.ralf.www.recyclerviewtest.entity.GanHuo;

import java.util.List;

/**
 * @author Ralf
 * DESCRIPTION
 * @name PictureAdapter
 * @date 2018/09/19 上午12:18
 **/
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {

    private List<GanHuo> picList;
    private ItemClickListener mClickListener;
    private Context mContext;

    public PictureAdapter(Context context, List<GanHuo> picList) {
        this.picList = picList;
        mContext = context;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_activity_rv,
                parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PictureViewHolder holder, final int position) {
        if (picList != null) {
            GanHuo ganHuo = picList.get(position);
            String url = ganHuo.getUrl();
            String who = ganHuo.getWho();
            RequestOptions requestOptions = RequestOptions.placeholderOf(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_refresh_white_24dp)
                    .useAnimationPool(true);
            Glide.with(mContext)
                    .load(url)
                    .apply(requestOptions)
                    .into(holder.imageView);
            holder.textView.setText(who);
            // 点击事件
            if (mClickListener != null) {
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.onItemClick(PictureAdapter.this, v, position);
                    }
                });
                holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mClickListener.onItemLongClick(PictureAdapter.this, v, position);
                        return true;
                    }
                });

                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.imageView.setBackground(mContext.getResources().getDrawable(R.drawable.item_press_background));
                        mClickListener.onItemClick(PictureAdapter.this, v, position);
                    }
                });
                holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        holder.imageView.setBackground(mContext.getResources().getDrawable(R.drawable.item_press_background));
                        mClickListener.onItemLongClick(PictureAdapter.this, v, position);
                        return true;
                    }
                });
            }
        } else {
            // default url
            RequestOptions requestOptions = RequestOptions.placeholderOf(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_refresh_white_24dp);
            Glide.with(mContext)
                    .load(R.mipmap.ic_launcher)
                    .apply(requestOptions)
                    .into(holder.imageView);
            holder.textView.setText("看不到的妹纸");
        }
    }

    @Override
    public int getItemCount() {
        if (picList == null || picList.size() < 1) {
            return 0;
        }
        return picList.size();
    }

    static class PictureViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private ImageView imageView;
        private TextView textView;

        public PictureViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            initView();
        }

        private void initView() {
            imageView = mView.findViewById(R.id.iv_meizhi);
            textView = mView.findViewById(R.id.tv_title);
        }
    }

    /**
     * 添加测试，用于展现动画
     *
     * @param position
     */
    public void insertTest(int position) {
        picList.add(position, picList.get(picList.size() - 1));
        // 这里更新数据集不是用adapter.notifyDataSetChanged()而是
        // notifyItemInserted(position)与notifyItemRemoved(position)
        // 否则没有动画效果。
        notifyItemInserted(position);
    }

    /**
     * 删除测试，用于展现动画
     *
     * @param position
     */
    public void removeTest(int position) {
        picList.remove(picList.get(position));
        notifyItemRemoved(position);
    }

    public ItemClickListener getClickListener() {
        return mClickListener;
    }

    public void setClickListener(ItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface ItemClickListener {

        void onItemClick(RecyclerView.Adapter adapter,
                         View view, int position);

        void onItemLongClick(RecyclerView.Adapter adapter,
                             View view, int position);
    }
}
