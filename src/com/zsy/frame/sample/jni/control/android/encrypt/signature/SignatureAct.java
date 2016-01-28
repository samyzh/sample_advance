package com.zsy.frame.sample.jni.control.android.encrypt.signature;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zsy.frame.lib.ui.activity.SYBaseAct;
import com.zsy.frame.lib.ui.annotation.BindView;
import com.zsy.frame.sample.jni.R;

public class SignatureAct extends SYBaseAct {
	static {
		System.loadLibrary("android_encrypt_signature_SignatureAct");
	}

	public native String get_apk_signature();

	@BindView(id = R.id.button1, click = true)
	private TextView textView1;
	@BindView(id = R.id.button1, click = true)
	private Button button1;
	@BindView(id = R.id.button2, click = true)
	private TextView textView2;
	@BindView(id = R.id.button2, click = true)
	private Button button2;

	@Override
	public void setRootView() {
		setContentView(R.layout.activity_android_encrypt_signature_signature);
	}

	@Override
	protected void initWidget(Bundle savedInstanceState) {
		super.initWidget(savedInstanceState);
	}

	@Override
	public void widgetClick(View v) {
		super.widgetClick(v);
		switch (v.getId()) {
			case R.id.button1:
				textView1.setText(get_signature_from_java() + "");
				break;
			case R.id.button2:
				textView2.setText(get_apk_signature() + "");
				break;
		}
	}

	private int get_signature_from_java() {
		Signature sign = null;
		try {
			PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 64);

			sign = info.signatures[0];

			Log.i("test", "hashCode : " + sign.hashCode());

		}
		catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return sign.hashCode();
	}
}
