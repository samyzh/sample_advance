package com.zsy.frame.sample.jni.control.android.multimedia.speex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.boyaa.speech.SpeechController;
import com.boyaa.speech.SpeechListener;
import com.boyaa.speech.util.FileUtil;
import com.zsy.frame.sample.jni.R;

/**
 * @description：
 * 在Android开发中，需要录音并发送到对方设备上。这时问题来了，手机常会是GPRS、3G等方式上网，
 * 所以节省流量是非常关键的，使用Speex来压缩音频文件，可以将音频压文件小数倍。
 * 
 * Speex是一套主要针对语音的开源免费，无专利保护的音频压缩格式。
 * Speex工程着力于通过提供一个可以替代高性能语音编解码来降低语音应用输入门槛 。
 * 另外，相对于其它编解码，Speex也很适合网络应用，在网络应用上有着自己独特的优势。
 * 同时，Speex还是GNU工程的一部分，在改版的BSD协议中得到了很好的支持。
 * Speex是基于CELP并且专门为码率在2-44kbps的语音压缩而设计的。
 * Speex源码是基于c语音实现的（也有java实现，效率相对较低）。
 * 参考：
  http://code.google.com/p/android-recorder/downloads/list  这个是一个android recorder ，使用speex编码，
  
  现发现用到的第三方分装库jar处理机制跟Lame中的SimpleLame一致；
 * @author samy
 * @date 2015-4-12 下午11:43:17
 */
public class SpeechExtendAct extends Activity implements OnClickListener {
	private SpeechController mSpeechController;

	private String fileName = null;
	private int mSampleRate;

	private TextView messageTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_multimedia_speex_speech_extend);

		initViews();

		mSpeechController = SpeechController.getInstance();
		mSpeechController.setDebug(true);
		mSpeechController.setRecordingMaxTime(10);
		mSpeechController.setSpeechListener(new SpeechListener() {

			@Override
			public void timeConsuming(int type, int secondCount, Object tag) {
				Log.d("CDH", "SpeechListener secondCount:" + secondCount);
				if (type == SpeechListener.RECORDING) {
					setMessage("正在录音（" + secondCount + "秒）....");
				}
				else if (type == SpeechListener.PLAYING) {
					setMessage("正在播放（" + secondCount + "秒）....");
				}
			}

			@Override
			public void recordOver(int sampleRate) {
				Log.d("CDH", "SpeechListener recordOver(" + sampleRate + ")");
				mSampleRate = sampleRate;
				setMessage("录音结束");
			}

			@Override
			public void playOver(Object tag) {
				Log.d("CDH", "SpeechListener playOver(" + tag + ")");
				setMessage("播放结束");
			}

			@Override
			public void recordingVolume(int volume) {
				Log.i("CDH", "SpeechListener recordVolume(" + volume + ")");
			}

		});
	}

	private void setMessage(final String message) {
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				messageTextView.setText(message);
			}

		});
	}

	private void initViews() {
		messageTextView = (TextView) this.findViewById(R.id.message);

		this.findViewById(R.id.start_record).setOnClickListener(this);
		this.findViewById(R.id.stop_record).setOnClickListener(this);
		this.findViewById(R.id.play).setOnClickListener(this);
		this.findViewById(R.id.stop_play).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// 开始录音
		if (v.getId() == R.id.start_record) {
			fileName = FileUtil.getRandomFileName();
			Log.d("CDH", "语音文件名:" + fileName);

			SharedPreferences spf = getSharedPreferences("Speech", MODE_PRIVATE);
			String fn = spf.getString("file_name", null);
			if (TextUtils.isEmpty(fn)) {
				spf.edit().putString("file_name", fileName);
			}

			// 建议语音文件存放在外存（SD卡），因为内置存储一般空间不大
			FileOutputStream fileOutputStream;
			try {
				fileOutputStream = this.openFileOutput(fileName, MODE_PRIVATE);
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new IllegalStateException("output file not found", e);
			}

			messageTextView.setText("正在录音....");
			mSpeechController.startRecord(fileOutputStream);
		}
		// 停止录音
		else if (v.getId() == R.id.stop_record) {
			messageTextView.setText("停止录音");
			mSpeechController.stopRecord();
		}
		// 播放
		else if (v.getId() == R.id.play) {
			String tempFileName = null;
			if (!TextUtils.isEmpty(fileName)) {
				tempFileName = fileName;
			}
			else {
				SharedPreferences spf = getSharedPreferences("Speech", MODE_PRIVATE);
				String fn = spf.getString("file_name", null);
				if (!TextUtils.isEmpty(fn)) {
					tempFileName = fn;
				}
			}
			if (TextUtils.isEmpty(tempFileName)) {
				messageTextView.setText("先录音才能播放");
				return;
			}
			FileInputStream fileInputStream;
			try {
				fileInputStream = this.openFileInput(tempFileName);
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new IllegalStateException("input file not found", e);
			}
			messageTextView.setText("正在播放....");
			mSpeechController.play(fileInputStream, mSampleRate, tempFileName);
		}
		// 停止播放
		else if (v.getId() == R.id.stop_play) {
			messageTextView.setText("停止播放");
			mSpeechController.stopPlay();
		}
	}

}
