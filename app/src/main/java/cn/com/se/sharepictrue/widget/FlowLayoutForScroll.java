package cn.com.se.sharepictrue.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.wefika.flowlayout.FlowLayout;

/**
 * Created by KIDNG on 2015/11/7.
 */
public class FlowLayoutForScroll extends FlowLayout {

    public FlowLayoutForScroll(Context context) {
        super(context);
    }

    public FlowLayoutForScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayoutForScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
