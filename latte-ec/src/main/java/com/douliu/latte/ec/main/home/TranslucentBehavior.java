package com.douliu.latte.ec.main.home;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.douliu.latte.ec.R;

/**
 * Description:Toolbar相对RecyclerView的滑动实现一个透明度渐变的动画效果
 * Author:douliu
 * Created on 2017/9/25.
 */

@SuppressWarnings("unused")
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    private int mDistanceY = 0;

    private static final int color = 0xffffbb33;

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.recycler;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        //计算y方向滑动的距离
        mDistanceY += dy;
        //获取toolbar的高度
        final int height = child.getHeight();
        if (mDistanceY >= 0 && mDistanceY <= height) {

            float fraction = mDistanceY / height;
            //做一个透明渐变的动画
            float alpha = 255 * fraction;

            child.setBackgroundColor(Color.argb((int) alpha, Color.red(color),Color.green(color),Color.blue(color)));

        } else if (mDistanceY > height) {
            child.setBackgroundColor(color);
        }

    }


}
