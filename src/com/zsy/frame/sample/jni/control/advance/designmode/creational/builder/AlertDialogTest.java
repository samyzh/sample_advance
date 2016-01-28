package com.zsy.frame.sample.jni.control.advance.designmode.creational.builder;

import com.zsy.frame.sample.jni.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogTest {
	// 显示基本的AlertDialog
	private void showDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("Title");
		builder.setMessage("Message");
		builder.setPositiveButton("Button1", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// setTitle("点击了对话框上的Button1");
			}
		});
		builder.setNeutralButton("Button2", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// setTitle("点击了对话框上的Button2");
			}
		});
		builder.setNegativeButton("Button3", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// setTitle("点击了对话框上的Button3");
			}
		});
		builder.create().show(); // 构建AlertDialog， 并且显示
	}
}
