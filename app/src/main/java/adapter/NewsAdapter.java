package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cleverboy.news.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import bean.NewsEntity;

/**
 * Created by yls on 2017/6/28.
 */

public class NewsAdapter extends BaseAdapter {

    private Context context;
    /** 列表显示的新闻数据*/
    private List<NewsEntity.ResultBean> listDatas;

    public NewsAdapter(Context context, List<NewsEntity.ResultBean> listDatas) {
        this.context = context;
        this.listDatas = listDatas;
    }

    @Override
    public int getCount() {
        return (listDatas == null) ? 0:listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsEntity.ResultBean info = (NewsEntity.ResultBean) getItem(position);
        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_news_1,null);
        }
        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
        TextView tvTile = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tvSource = (TextView) convertView.findViewById(R.id.tv_source);
        TextView tvComment = (TextView) convertView.findViewById(R.id.tv_comment);
        tvTile.setText(info.getTitle());
        tvSource.setText(info.getSource());
        tvComment.setText(info.getReplyCount()+"跟帖");

        Picasso.with(context).load(info.getImgsrc()).into(ivIcon);

        return convertView;
    }
}
