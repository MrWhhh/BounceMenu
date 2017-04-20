# BounceMenuDemo
仿新浪微博弹跳菜单

![image](https://github.com/MrWhhh/BounceMenu/raw/master/img/gif1.gif)

本人菜鸡一枚，站在巨人的肩膀上实现的一个菜单弹跳效果，具体可以down项目看一下。
代码很简单，希望大家给个star。

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.MrWhhh:BounceMenu:1.0.0'
	}

布局文件中使用：

	bounceMenuView = (BounceMenuView) findViewById(R.id.bouncemenu);
        List<Menu> data = new ArrayList<>();
        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
        data.add(new Menu(R.mipmap.ic_launcher,"哈哈哈"));
        bounceMenuView.setLineCount(3);
        bounceMenuView.setSpaceHeight(30);
        bounceMenuView.setData(data);
	
Dialog使用：

	dialog = new BounceMenuDialog();
        dialog.addItem(new Menu(R.mipmap.ic_launcher, "ccc"))
                .addItem(new Menu(R.mipmap.ic_launcher, "ccc"))
                .addItem(new Menu(R.mipmap.ic_launcher, "ccc"))
                .addItem(new Menu(R.mipmap.ic_launcher, "ccc"))
                .addItem(new Menu(R.mipmap.ic_launcher, "ccc"))
                .addItem(new Menu(R.mipmap.ic_launcher, "ccc"))
                .addItem(new Menu(R.mipmap.ic_launcher, "ccc")).build(this).setLineCount(4).setSpaceHeight(10);
        dialog.show();
