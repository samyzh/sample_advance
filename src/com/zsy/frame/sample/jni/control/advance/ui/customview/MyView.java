package com.zsy.frame.sample.jni.control.advance.ui.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public MyView(Context context) {
		super(context);
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	private Paint paint = new Paint();
	private int line_x = 100, line_y = 100;
	private float count = 0;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (count > 12) {
			count = 0;
		}
		int x = (int) (75.0 * Math.cos(2 * Math.PI * count / 12.0));
		int y = (int) (75.0 * Math.cos(2 * Math.PI * count / 12.0));
		count++;
		
		canvas.drawColor(Color.WHITE);
		
		paint.setStrokeWidth(15);
		paint.setColor(Color.YELLOW);
		canvas.drawLine(line_x, line_y, line_x + x, line_y + y, paint);
		
		paint.setStrokeWidth(2);
		paint.setColor(Color.GREEN);
		canvas.drawRect(line_x - 5, line_y - 5, line_x + 5, line_y + 5, paint);
		
		paint.setColor(Color.BLUE);
		canvas.drawRect(line_x - 3, line_y - 3, line_x + 3, line_y + 3, paint);
		
		
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		invalidate();
	}

}
