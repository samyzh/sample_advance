package com.zsy.frame.sample.jni.control.android.multimedia.lame;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zsy.frame.sample.jni.R;

public class AudioRecorderAct extends Activity {
private TextView textView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_multimedia_lame_audio_recorder);
		
		textView1 = (TextView)findViewById(R.id.textView1);

		final Mp3Recorder recorder = new Mp3Recorder();
		(findViewById(R.id.record)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					recorder.startRecording();
					textView1.setText("正在录音中....\n录音存放的位置为：/AudioRecorder/..");
				}
				catch (IOException e) {
					Log.d("MainActivity", "Start error");
				}
			}
		});

		(findViewById(R.id.stop)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					recorder.stopRecording();
					textView1.setText("录音完毕");
				}
				catch (IOException e) {
					Log.d("MainActivity", "Stop error");
				}
			}
		});
	}
}
