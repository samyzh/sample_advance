package com.zsy.frame.sample.jni.control.advance.service.base;

import java.util.Timer;
import java.util.TimerTask;

import com.zsy.frame.lib.SYLoger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class CountSer extends Service {
	private static ICountListener serListener;
	private static int k = 0;
	private Timer timer = new Timer();

	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (null != serListener) {
				serListener.update(String.valueOf(k++));
			}
		};
	};

	public void onCreate() {
		super.onCreate();
		Thread.currentThread().setName(Thread.currentThread().getName()+"-CountSer");
		Toast.makeText(this, Thread.currentThread().getName(), Toast.LENGTH_LONG).show();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0);
			}
		};
		timer.schedule(timerTask, 1000, 2000);
	};

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		timer.cancel();
	}

	/**
	 * @description：传递接收设置Activity中的接口
	 * @author samy
	 * @date 2015-3-26 下午8:58:03
	 */
	public static void setUpdateListener(ICountListener countListener) {
		serListener = countListener;
	}

}
