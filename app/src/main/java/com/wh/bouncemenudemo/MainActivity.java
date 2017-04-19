package com.wh.bouncemenudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wh.bouncemenu.BounceMenuDialog;
import com.wh.bouncemenu.BounceMenuView;
import com.wh.bouncemenu.Menu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    BounceMenuView bounceMenuView;

    boolean isOpen = false;

    BounceMenuDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.click).setOnClickListener(this);
//        bounceMenuView = (BounceMenuView) findViewById(R.id.bouncemenu);
//        List<Menu> data = new ArrayList<>();
//        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
//        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
//        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
//        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
//        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
//        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
//        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
//        bounceMenuView.setLineCount(3);
//        bounceMenuView.setSpaceHeight(30);
//        bounceMenuView.setData(data);

    }

    @Override
    public void onClick(View v) {
        dialog = new BounceMenuDialog();
        dialog.addItem(new Menu(R.mipmap.ic_launcher, "ccc"))
                .addItem(new Menu(R.mipmap.ic_launcher, "ccc"))
                .addItem(new Menu(R.mipmap.ic_launcher, "ccc"))
                .addItem(new Menu(R.mipmap.ic_launcher, "ccc"))
                .addItem(new Menu(R.mipmap.ic_launcher, "ccc"))
                .addItem(new Menu(R.mipmap.ic_launcher, "ccc"))
                .addItem(new Menu(R.mipmap.ic_launcher, "ccc")).build(this).setLineCount(4).setSpaceHeight(10);
        dialog.show();
//        if (isOpen) {
//            bounceMenuView.outAnim(3);
//            isOpen = false;
//        } else {
//            bounceMenuView.startAnim(3);
//            isOpen = true;
//        }
    }
}
