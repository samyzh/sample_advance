package com.zsy.frame.sample.jni.control.android.multimedia.lame;

import java.io.File;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zsy.frame.sample.jni.R;

/**
 * @description：
 * LAME是目前最好的MP3编码引擎。LAME编码出来的MP3音色纯厚、空间宽广、低音清晰、细节表现良好，它独创的心理音响模型技术保证了CD音频还原的真实性，配合VBR和ABR参数，音质几乎可以媲美CD音频，但文件体
 * 积却非常小。对于一个免费引擎，LAME的优势不言而喻。
 *用于场景：
 *Android下使用lamemp3库将PCM录音数据压缩为MP3格式（详细见http://blog.csdn.net/gf771115/article/details/37113143）
 *PCM是无损的，但不一定是最高音质格式;就是wav格式；
 *在做项目时参考的一些文章供大家研究：
http://wenku.baidu.com/view/70cebd3e580216fc700afd51?from_page=view&from_mod=download 这个有JNI部分使用
http://ikinglai.blog.51cto.com/6220785/1228730   这个在下面有附件的
http://www.cnblogs.com/Amandaliu/archive/2013/02/04/2891604.html 录音的
https://github.com/talzeus/AndroidMp3Recorder 这个是直接就可以录制成MP3的
http://developer.samsung.com/android/technical-docs/Porting-and-using-LAME-MP3-on-Android-with-JNI在android如何修改libmp3lame

 *现在做的写死的wav格式文件写死，后期可以直接从apk的asset中文件拷贝到sd里面做格式转换
 * @author samy
 * @date 2015-4-12 下午3:25:56
 */
public class FormatConversionAct extends Activity {
	static {
		System.loadLibrary("android_multimedia_lame_FormatConversionAct");
	}

	public native void convertmp3(String wav, String mp3);

	/**
	 * @description：底层调用方法,获取转换进度；
	 * @author samy
	 * @date 2014年10月7日 下午5:33:18
	 */
	public void setConvertProgress(int progress) {
		pd.setProgress(progress);
	}

	/**
	 * @description：待实现功能；
	 * @author samy
	 * @date 2014年10月7日 下午8:00:26
	 */
	public native void stop();

	public native String getLameVersion();

	private EditText et_wav;
	private EditText et_mp3;
	private ProgressDialog pd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_multimedia_lame_format_conversion);
		et_wav = (EditText) this.findViewById(R.id.editText1);
		et_mp3 = (EditText) this.findViewById(R.id.editText2);
		pd = new ProgressDialog(this);
	}

	/**
	 * @description：点击转化触发；
	 * @author samy
	 * @date 2014年10月7日 下午8:08:23
	 */
	public void convert(View view) {
		final String mp3name = et_mp3.getText().toString().trim();
		final String wavname = et_wav.getText().toString().trim();
		File file = new File(wavname);
		int size = (int) file.length();
		System.out.println("文件大小 " + size);
		if ("".equals(mp3name) || "".equals(wavname)) {
			Toast.makeText(this, "路径不能为空", 1).show();
			return;
		}
		// convertmp3(wavname,mp3name);
		pd.setMessage("转换中....");
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMax(size); // 设置进度条的最大值
		// pd.setCancelable(false);
		pd.show();
		new Thread() {
			@Override
			public void run() {
				convertmp3(wavname, mp3name);
				pd.dismiss();
			}

		}.start();
	}

	/**
	 * @description：获取Lame版本号；
	 * @author samy
	 * @date 2014年10月7日 下午5:33:18
	 */
	public void getversion(View view) {
		String version = getLameVersion();
		Toast.makeText(this, version, 0).show();
	}
}