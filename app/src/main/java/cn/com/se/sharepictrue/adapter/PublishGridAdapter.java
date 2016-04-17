package cn.com.se.sharepictrue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import cn.com.se.sharepictrue.R;

/**
 * 发布界面gridView的adapter
 * Created by KIDNG on 2015/11/6.
 */
public class PublishGridAdapter extends BaseAdapter {
    private List<String> mPathList;
    private Context mContext;

    public PublishGridAdapter(List<String> pathList, Context context) {
        mPathList = pathList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mPathList.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return position==mPathList.size()+1?null:mPathList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_publish_grid,parent,false);
            holder = new ViewHolder();
            holder.img = (ImageView) view.findViewById(R.id.iv_item_publish_img);
            holder.del = (ImageView) view.findViewById(R.id.iv_item_publish_del);
            view.setTag(holder);
            convertView = view;
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position == mPathList.size()+1){
            holder.img.setImageResource(R.mipmap.ic_add_img);
        }
        return null;
    }

    class ViewHolder{
        public ImageView img;
        public ImageView del;
    }
}
