package com.example.administrator.medical.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.medical.R;
import com.example.administrator.medical.pojo.NewsListPolo;
import com.example.administrator.medical.utils.DataUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/7/21.
 */
public class NewsAdapter extends NewsBaseAdapter {

    private ArrayList<NewsListPolo> poloArrayList = null;
    private RecyclerItemClickerListener itemClickerListener;

    public NewsAdapter(Context context, RecyclerItemClickerListener itemClickerListener) {
        super(context);
        poloArrayList = new ArrayList<>();
        this.itemClickerListener = itemClickerListener;
    }

    public void addRefreshData(ArrayList<NewsListPolo> pojos) {
        if (poloArrayList != null) {
            poloArrayList.addAll(0, pojos);
            notifyItemRangeInserted(0, pojos.size());
        }
    }

    public void addMoreData(ArrayList<NewsListPolo> polos) {
        if (poloArrayList != null) {
            poloArrayList.addAll(poloArrayList.size(), polos);
            notifyItemRangeInserted(poloArrayList.size(), polos.size());
        }
    }

    public void clear(){
        if (poloArrayList != null){
            poloArrayList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    protected RecyclerView.ViewHolder initViewHolder(ViewGroup parent, int viewType) {
        return new NewsListViewHolder(inflater.inflate(R.layout.item_new_list, parent, false));
    }

    @Override
    protected void bindData(RecyclerView.ViewHolder holder, int position) {
        NewsListViewHolder viewHolder = (NewsListViewHolder) holder;
        viewHolder.recenter(poloArrayList.get(position));
    }

    public NewsListPolo getItem(int position){
        return poloArrayList.get(position);
    }

    @Override
    protected int setDataSize() {
        return poloArrayList.size();
    }

    public class NewsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;
        private TextView title;
        private TextView subTitle;
        private TextView time;

        public NewsListViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.item_new_list_image);
            title = (TextView) itemView.findViewById(R.id.item_new_list_title);
            subTitle = (TextView) itemView.findViewById(R.id.item_new_list_subtitle);
            time = (TextView) itemView.findViewById(R.id.item_new_list_time);
        }

        public void recenter(NewsListPolo listPolo) {
            if (listPolo != null) {
                imageDisplay.loadImageUrl(listPolo.getImg(), imageView, R.mipmap.umeng_socialize_share_pic);
                title.setText(listPolo.getTitle());
                subTitle.setText(listPolo.getTag());
                time.setText(DataUtils.dateFormat(listPolo.getTime()));
            }
        }

        @Override
        public void onClick(View v) {
            itemClickerListener.onItemClick(getPosition());
        }
    }
}
