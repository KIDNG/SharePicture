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

import java.util.List;

/**
 * Abstraction class of a BaseAdapter in which you only need to provide the
 * convert() implementation.<br/>
 * Using the provided BaseAdapterHelper, your code is minimalist.
 *
 * @param <T>
 *            The type of the items in the list.
 */
public abstract class QuickAdapter<T> extends BaseQuickAdapter<T, BaseAdapterHelper> {

    public QuickAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public QuickAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    public QuickAdapter(Context context, List<T> data, MultiViewTypeSupport<T> multiViewTypeSupport) {
        super(context, data, multiViewTypeSupport);
    }

    protected BaseAdapterHelper getAdapterHelper(int position, View convertView, ViewGroup parent) {
        if (null != multiViewTypeSupport) {
            int layoutId = multiViewTypeSupport.getLayoutId(position, data.get(position));
            return BaseAdapterHelper.get(context, convertView, parent, layoutId, position);
        } else {
            return BaseAdapterHelper.get(context, convertView, parent, layoutResId, position);
        }
    }

}
