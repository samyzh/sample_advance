package com.zsy.frame.sample.jni.control.advance.service.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zsy.frame.lib.SYLoger;
import com.zsy.frame.sample.jni.R;

/**
 * @description：短程进程模拟处理数据
 * 其实这里演示就是startService用法
 * 通过打印进程的Name发现：上述的Activity和Service之外，还有BroadcastReceiver也是一样
 * @author samy
 * @date 2015-3-26 下午8:58:47
 */
public class CountAct extends Activity implements OnClickListener {
	private TextView textView1;
	private Button button1;
	private Button button2;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_service_base_count);

		textView1 = (TextView) findViewById(R.id.textView1);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);

		button1.setOnClickListener(this);
		CountSer.setUpdateListener(new UpdateUIListener());

		intent = new Intent(this, CountSer.class);

		/**
		 * 除了上述的Activity和Service之外，还有BroadcastReceiver也是一样，是由主线程来启动的；
		 * 例如：由一个Activity 启动一个BroadcastReceiver，二者都是同一个进程里执行；
		 */
		Thread.currentThread().setName(Thread.currentThread().getName() + "-CountAct");
		Toast.makeText(this, Thread.currentThread().getName(), Toast.LENGTH_LONG).show();
	}

	class UpdateUIListener implements ICountListener {
		@Override
		public void update(String s) {
			textView1.setText("" + s);
		}
	}

	@Override
	protected void onDestroy() {
		if (null != intent) {
			stopService(intent);
		}
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button1:
				// Intent { cmp=com.zsy.frame.sample.jni/.control.advance.service.base.CountSer }
				startService(intent);
				break;
			case R.id.button2:
				if (null != intent) {
					stopService(intent);
				}
				break;
		}
	};
}
