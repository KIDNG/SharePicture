/**
 * Copyright 2013 Joan Zapata
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.se.sharepictrue.quickadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstraction class of a BaseAdapter in which you only need to provide the
 * convert() implementation.<br/>
 * Using the provided BaseAdapterHelper, your code is minimalist.
 *
 * @param <T> The type of the items in the list.
 */
public abstract class BaseQuickAdapter<T, H extends BaseAdapterHelper> extends BaseAdapter {

    protected final Context context;
    protected int layoutResId;
    protected final List<T> data;

    public BaseQuickAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    public BaseQuickAdapter(Context context, int layoutResId, List<T> data) {
        this.context = context;
        this.layoutResId = layoutResId;
        this.data = (null == data) ? new ArrayList<T>() : new ArrayList<T>(data);
    }

    protected MultiViewTypeSupport<T> multiViewTypeSupport;

    public BaseQuickAdapter(Context context, List<T> data, MultiViewTypeSupport<T> multiViewTypeSupport) {
        this.context = context;
        this.multiViewTypeSupport = multiViewTypeSupport;
        this.data = (null == data) ? new ArrayList<T>() : new ArrayList<T>(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (null != multiViewTypeSupport) {
            return multiViewTypeSupport.getViewTypeCount();
        }
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (null != multiViewTypeSupport){
            return multiViewTypeSupport.getItemViewType(position, data.get(position));
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final H helper = getAdapterHelper(position, convertView, parent);
        T item = getItem(position);
        convert(helper, item);

        return helper.getConvertView();
    }

    public void add(T elem) {
        data.add(elem);
        notifyDataSetChanged();
    }

    public void add(int location, T item) {
        data.add(location, item);
        notifyDataSetChanged();
    }

    public void addAll(List<T> elem) {
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public void addAll(int location, List<T> elem) {
        data.addAll(location, elem);
        notifyDataSetChanged();
    }

    public void set(int index, T elem) {
        data.set(index, elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        data.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        data.clear();
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {
        return data.contains(elem);
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    /**
     * Implement this method and use the helper to adapt the view to the given
     * item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(H helper, T item);

    /**
     * You can override this method to use a custom BaseAdapterHelper in order
     * to fit your needs
     *
     * @param position    The position of the item within the adapter's data set of the
     *                    item whose view we want.
     * @param convertView The old view to reuse, if possible. Note: You should check
     *                    that this view is non-null and of an appropriate type before
     *                    using. If it is not possible to convert this view to display
     *                    the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so
     *                    that this View is always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)} ).
     * @param parent      The parent that this view will eventually be attached to
     * @return An instance of BaseAdapterHelper
     */
    protected abstract H getAdapterHelper(int position, View convertView, ViewGroup parent);

}
