package com.zsy.frame.sample.jni.control.advance.service.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zsy.frame.lib.ui.activity.SYBaseAct;
import com.zsy.frame.lib.ui.annotation.BindView;
import com.zsy.frame.sample.jni.R;

/**
 * @description：远程进程实例
 * 如果没有去配置文件中配置;这里还是在同一个进程中执行；线程还是同一个进程；
 * 可见在IPC可用于近程通信也可用于远程通信；
 * 
 * context.startService() ->onCreate()- >onStart()-onStartCommand->Service running--调用context.stopService() ->onDestroy() 
 * context.bindService()->onCreate()->onBind()->Service running--调用>onUnbind() -> onDestroy() 
 * @author samy
 * @date 2015-3-26 下午10:07:26
 */
public class MusicAct extends SYBaseAct {
	@BindView(id = R.id.button1, click = true)
	private Button button1;
	@BindView(id = R.id.button2, click = true)
	private Button button2;
	@BindView(id = R.id.button3, click = true)
	private Button button3;

	// 判断是否绑定
	private boolean mBound;
	private IBinder ib;

	@Override
	public void setRootView() {
		setContentView(R.layout.activity_advance_service_base_music);
	}

	@Override
	protected void initWidget(Bundle savedInstanceState) {
		super.initWidget(savedInstanceState);
		Thread.currentThread().setName(Thread.currentThread().getName() + "-MusicAct");
		Toast.makeText(this, Thread.currentThread().getName(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void widgetClick(View v) {
		super.widgetClick(v);
		switch (v.getId()) {
			case R.id.button1:
				/**
				 * 其实这样设计处理的有点麻烦，后期可用ps模式，更方便，包装的接口更人性化；包装IBinder;
				 */
				Parcel pc = Parcel.obtain();//in
				Parcel pc_reply = Parcel.obtain();//out
				pc.writeString("playing");
				try {
					// 这个方法一定要crach;
//					调用AMS后，会触发调用BBinder_proxy代理
//					IBinder.LAST_CALL_TRANSACTION
					ib.transact(1, pc, pc_reply, 0);
				}
				catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
			case R.id.button2:
				pc = Parcel.obtain();
				pc_reply = Parcel.obtain();
				pc.writeString("stop");
				try {
					// 这个方法一定要crach;
					ib.transact(2, pc, pc_reply, 0);
				}
				catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
			case R.id.button3:
				finish();
				break;
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		// 现这里想隐式启动
		bindService(new Intent(this, MusicSer.class), connection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mBound) {
			unbindService(connection);
			mBound = false;
		}
	}

	/**
	 * 因为connection对象不是属于特定函数内的对象，而是属于类的对象；各函数都可用的公用对象；
	 * 这种static对象（或变量）都是 loading_time 就创建了；
	 * 
	 * App被Load近来之后，才会启动（建立）myActivity对象，之后才会调用myActivity 的oncreate函数；
	 */
	public ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			mBound = false;
		}

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder binder) {
			// 连接上，直接通过Ibinder绑定Messenger信使；传递消息对象给Service;
			// LocalBinder localBinder = (LocalBinder) binder;
			// localBinder = (LocalBinder) binder;
			// myService = localBinder.getService();
			mBound = true;
			ib = binder;
		}
	};

}
