package com.wh.bouncemenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringChain;

import java.util.List;

/**
 * @author MrWhhh .
 * @date : 2017/4/19 .
 * @description : .
 */
public class BounceMenuView extends ViewGroup {

    final String TAG = "BounceMenuView";

    List<Menu> list;

    int lineCount = 4;

    int spaceHeight = 0;

    Context context;

    int childWidth;

    BounceMenuInterface bounceMenuInterface;

    public BounceMenuView(Context context) {
        super(context);
    }

    public BounceMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        Log.w(TAG, "BounceMenuView: ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.w(TAG, "onMeasure: ");
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (null != list && list.size() > 0) {
            childWidth = widthSize / lineCount;
            if (heightMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(widthSize, getViewHeight());
            } else {
                setMeasuredDimension(widthSize, heightSize);
            }
            for (int i = 0; i < getChildCount(); i++) {
                LayoutParams lp = getChildAt(i).getLayoutParams();
                lp.width = childWidth;
                getChildAt(i).setLayoutParams(lp);
            }
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public int getViewHeight() {
        int childCount = getChildCount();
        int height = (int) (getChildAt(0).getMeasuredHeight() * Math.ceil((double) childCount / (double) lineCount));
        return height;
    }

    public void startAnim(int index) {
        SpringChain springChain = SpringChain.create(40, 6, 50, 7);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View view = getChildAt(i);

            springChain.addSpring(new SimpleSpringListener() {
                @Override
                public void onSpringUpdate(Spring spring) {
                    view.setTranslationY((float) spring.getCurrentValue());
                }
            });
        }

        List<Spring> springs = springChain.getAllSprings();
        for (int i = 0; i < springs.size(); i++) {
            springs.get(i).setCurrentValue(getHeight());
        }

        springChain.setControlSpringIndex(index).getControlSpring().setEndValue(0);
    }

    public void outAnim(int index) {
        SpringChain springChain = SpringChain.create(40, 6, 50, 7);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View view = getChildAt(i);

            springChain.addSpring(new SimpleSpringListener() {
                @Override
                public void onSpringUpdate(Spring spring) {
                    view.setTranslationY((float) spring.getCurrentValue());
                    Log.w(TAG, "onSpringUpdate: " + spring.getCurrentValue() + "height:" + getHeight());
//                    if (spring.getCurrentValue() > getHeight()) {
//                        animInterface.animEnd();
//                    }
                }

            });
        }

        List<Spring> springs = springChain.getAllSprings();
        for (int i = 0; i < springs.size(); i++) {
            springs.get(i).setCurrentValue(0);
        }
        springChain.setControlSpringIndex(index).getControlSpring().setEndValue(getHeight());
        springs.get(springs.size() - 1).addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                if (spring.getCurrentValue() > getHeight()) {
                    bounceMenuInterface.outAmimEnd();
                }
            }
        });
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            TextView tv = (TextView) getChildAt(i);

            int rowNum = i / lineCount;
            int columnNum = i % lineCount;

            int left = childWidth * columnNum;
            int top = getChildAt(i).getMeasuredHeight() * rowNum;
            int right = left + childWidth;
            int bottom = getChildAt(i).getMeasuredHeight() * rowNum + getChildAt(i).getMeasuredHeight();

            tv.layout(left, top, right, bottom);
        }
    }

    public void setData(List<Menu> list) {
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            addView(getTextView(i), new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
        requestLayout();
    }

    public TextView getTextView(final int position) {
        TextView tv = new TextView(context);
        tv.setText(list.get(position).title);
        Drawable icon = ContextCompat.getDrawable(context, list.get(position).icon);
        icon.setBounds(0, 0, icon.getMinimumWidth(), icon.getMinimumHeight());
        tv.setCompoundDrawables(null, icon, null, null);
        tv.setCompoundDrawablePadding(spaceHeight);
        tv.setGravity(Gravity.CENTER);
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bounceMenuInterface.onItemClick(position);
            }
        });
        return tv;
    }

    public void setLineCount(int lineCount) {
        Log.w(TAG, "setRowAndColumn: ");
        this.lineCount = lineCount;
    }


    public void setSpaceHeight(int spaceHeight) {
        this.spaceHeight = spaceHeight;
    }

    public void endCallBack(BounceMenuInterface bounceMenuInterface) {
        this.bounceMenuInterface = bounceMenuInterface;
    }

    public interface BounceMenuInterface {
        void outAmimEnd();

        void onItemClick(int position);
    }
}
