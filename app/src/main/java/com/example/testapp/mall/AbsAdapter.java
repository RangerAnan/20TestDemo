package com.example.testapp.mall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * adapter基类
 * Created by wzy on 2017/9/16
 */
public abstract class AbsAdapter<T, V> extends BaseAdapter {

    public List<T> dataList;

    private OnAdapterItemClickListener<T, V> listener;

    public void setListener(OnAdapterItemClickListener<T, V> listener) {
        this.listener = listener;
    }

    public interface OnAdapterItemClickListener<T, V> {
        void onItemInnerClick(View v, V holder, T t);
    }

    public AbsAdapter(List<T> dataList) {
        this.dataList = dataList;
    }

    public void setDataList(List<T> dataList) {
        if (dataList == null) {
            return;
        }
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        V holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), null);
            holder = getViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (V) convertView.getTag();
        }
        setListener(holder, position);
        fillData(holder, getItem(position));
        return convertView;
    }

    protected abstract int getLayoutId();

    protected abstract V getViewHolder(View itemView);

    protected abstract void setListener(V holder, int position);

    protected abstract void fillData(V holder, T t);

    public class MyOnClickListener implements View.OnClickListener {

        private V holder;
        private int position;

        public MyOnClickListener(V holder, int position) {
            this.holder = holder;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            T t = getItem(position);
            if (t == null) {
                return;
            }
            if (listener != null) {
                listener.onItemInnerClick(v, holder, t);
            }
        }
    }
}