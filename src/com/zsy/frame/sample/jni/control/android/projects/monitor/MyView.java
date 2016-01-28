package com.zsy.frame.sample.jni.control.android.projects.monitor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
	private int bottom;
	private Paint paint;

	public MyView(Context context, int bottom, int color) {
		super(context);
		this.bottom = bottom;
		paint = new Paint();
		paint.setColor(color);
		paint.setStrokeWidth(10);
	}

	// 所有android下的view控件的显示 其实是通过 ondraw
	// canvas 代表的是屏幕的画布
	@Override
	protected void onDraw(Canvas canvas) {
		// Paint paint = new Paint();
		// paint.setColor(Color.RED);
		// paint.setStrokeWidth(10);
		// paint.setTextSize(20);
		//
		// canvas.drawText("哥是被画出来的", 20, 20, paint);

		// bottom值 需要根据锅炉的压力 动态确定
		canvas.drawRect(20, 20, 30, bottom, paint);
		super.onDraw(canvas);
	}

}
