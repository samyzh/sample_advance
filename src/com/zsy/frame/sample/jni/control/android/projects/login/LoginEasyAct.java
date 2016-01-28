package com.zsy.frame.sample.jni.control.android.projects.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zsy.frame.sample.jni.R;

public class LoginEasyAct extends Activity {
	static {
		System.loadLibrary("android_projects_login_LoginEasyAct");
	}

	public native int call_login(String name, String pwd);

	private EditText et_name;
	private EditText et_password;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_projects_login_logineasy);
		et_name = (EditText) this.findViewById(R.id.editText1);
		et_password = (EditText) this.findViewById(R.id.editText2);

	}

	public void login(View view) {
		String name = et_name.getText().toString().trim();
		String password = et_password.getText().toString().trim();
		if (TextUtils.isEmpty(name)||TextUtils.isEmpty(password)) {
			return;
		}
		// 调用c代码 实现登陆的操作
		int result = call_login(name, password);
		if (result == 200) {
			Toast.makeText(this, "登陆成功", 0).show();
		}
		else {
			Toast.makeText(this, "登陆失败" + result, 0).show();
		}
	}
}