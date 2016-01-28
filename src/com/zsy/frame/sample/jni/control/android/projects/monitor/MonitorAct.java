package com.zsy.frame.sample.jni.control.android.projects.monitor;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MonitorAct extends Activity {
	static {
		System.loadLibrary("android_projects_monitor_MonitorAct");
	}

	public native int getPressure();

	private Timer timer;
	private TimerTask task;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			int pressure = (Integer) msg.obj;
			int color = getColor(pressure);
			if (color == 404) {
				TextView tv = new TextView(MonitorAct.this);
				tv.setTextColor(Color.RED);
				tv.setTextSize(30);
				tv.setText("锅炉快要爆炸了 快跑吧!");
				// 播放报警声音
				setContentView(tv);
				timer.cancel();
				return;
			}

			MyView myview = new MyView(MonitorAct.this, pressure, color);
			setContentView(myview);
			super.handleMessage(msg);
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 获取锅炉压力 ,根据压力显示不同的内容
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				// 300以内的随机数
				int pressure = getPressure() % 300;
				System.out.println("压力" + pressure);
				// 把压力显示到ui界面上
				Message msg = new Message();
				msg.obj = pressure;
				handler.sendMessage(msg);
			}
		};
		timer.schedule(task, 1000, 2000);
	}

	/**
	 * 300以内的随机数
	 * 根据锅炉压力 获取应该显示的颜色
	 * @param pressure
	 * @return
	 */
	public int getColor(int pressure) {
		if (pressure < 100) {
			return Color.GREEN;
		}
		else if (pressure < 200) {
			return Color.YELLOW;
		}
		else if (pressure < 260) {
			return Color.RED;
		}
		else {
			return 404;
		}
	}
}