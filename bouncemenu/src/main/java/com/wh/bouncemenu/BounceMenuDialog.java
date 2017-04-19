package com.wh.bouncemenu;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MrWhhh .
 * @date : 2017/4/19 .
 * @description : 弹出dialog.
 */
public class BounceMenuDialog implements BounceMenuView.BounceMenuInterface {

    Dialog mDialog;

    Context context;

    View mView;

    BounceMenuView bounceMenuView;

    List<Menu> list = new ArrayList<>();

    public BounceMenuDialog build(Context context) {
        this.context = context;
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_bounce_menu, null);
        bounceMenuView = (BounceMenuView) mView.findViewById(R.id.dialog_menu);
        bounceMenuView.endCallBack(this);
        mDialog = new Dialog(context, R.style.BounceMenuDialog);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.addContentView(mView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mDialog.setContentView(mView);
        bounceMenuView.setData(list);
        return this;
    }

    public BounceMenuDialog addItem(Menu item) {
        list.add(item);
        return this;
    }

    public void show() {
        mDialog.show();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.width = wm.getDefaultDisplay().getWidth();
        lp.height = wm.getDefaultDisplay().getHeight();
        mDialog.getWindow().setAttributes(lp);
        mView.post(new Runnable() {
            @Override
            public void run() {
                bounceMenuView.startAnim(0);
            }
        });
    }

    public void dismiss() {
        bounceMenuView.outAnim(0);
    }

    public BounceMenuDialog setLineCount(int count) {
        bounceMenuView.setLineCount(count);
        return this;
    }

    public BounceMenuDialog setSpaceHeight(int height) {
        bounceMenuView.setSpaceHeight(height);
        return this;
    }

    @Override
    public void outAmimEnd() {
        mDialog.dismiss();
    }

    @Override
    public void onItemClick(int position) {
        bounceMenuView.outAnim(0);
    }

//        bounceMenuView.setLineCount(3);
//        bounceMenuView.setSpaceHeight(30);
//        bounceMenuView.setData(data);
}
