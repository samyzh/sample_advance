package com.zsy.frame.sample.jni.control.advance.service.base;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.widget.Toast;

import com.zsy.frame.sample.jni.R;

/**
 * @description：对应音乐服务类
 * @author samy
 * @date 2015-4-9 下午9:11:54
 */
public class MusicSer extends Service {
	private IBinder iBinder = null;

	@Override
	public void onCreate() {
		super.onCreate();
		iBinder = new MusicBinder(this);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// 也可以写在这里
		// iBinder = new MusicBinder();
		return iBinder;
	}

	public class MusicBinder extends Binder {
		private MediaPlayer mediaPlayer = null;
		private Context context;

		public MusicBinder(Context context) {
			super();
			this.context = context;
		}

		@Override
		protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
//			android 的每一个进程里，通常含有一个线程池,让跨进程的线程得以进行:
//			注意这里是在remote的进程中线程池中，启动一个线程：
			Thread.currentThread().setName(Thread.currentThread().getName() + "-MusicSer");
			Toast.makeText(MusicSer.this, Thread.currentThread().getName(), Toast.LENGTH_LONG).show();
			
			reply.writeString(data.readString() + "mp3");
			if (code == 1) {
				this.play();
			}
			else if (code == 2) {
				this.stop();
			}
			return true;
			// return super.onTransact(code, data, reply, flags);
		}

		public void play() {
			/**
			 * 方法一：直接从res中的raw找打文件播放；
			 */
			if (mediaPlayer == null) {
				// mediaPlayer = MediaPlayer.create(context, R.raw.alarm_ring);
				mediaPlayer = MediaPlayer.create(context, R.raw.beep);
				mediaPlayer.setLooping(true);
			}
			try {
				mediaPlayer.prepare();
			}
			catch (IllegalStateException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			mediaPlayer.start();

			/**
			 * 方法二：从asset中音频文件播放；	  
			 */
//			 try {
//			 AssetManager assetManager = getAssets();
//			 AssetFileDescriptor fileDescriptor = assetManager.openFd("alarm_ring.mp3");
//			 mediaPlayer = new MediaPlayer();
//			 mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
//			 mediaPlayer.setLooping(true);
//			 mediaPlayer.prepare();
//			 mediaPlayer.start();
//			 }
//			 catch (Exception e) {
//			 e.printStackTrace();
//			 }
		}

		public void stop() {
			if (mediaPlayer != null) {
				mediaPlayer.stop();
				mediaPlayer.release();
				mediaPlayer = null;
			}
		}
	}

}

/**
 * 设置android:process=":remote"  还有点问题
 * 04-14 22:05:08.305: E/JavaBinder(10971): *** Uncaught remote exception!  (Exceptions are not yet supported across processes.)
04-14 22:05:08.305: E/JavaBinder(10971): java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
04-14 22:05:08.305: E/JavaBinder(10971): 	at android.os.Handler.<init>(Handler.java:200)
04-14 22:05:08.305: E/JavaBinder(10971): 	at android.os.Handler.<init>(Handler.java:114)
04-14 22:05:08.305: E/JavaBinder(10971): 	at android.widget.Toast$TN.<init>(Toast.java:351)
04-14 22:05:08.305: E/JavaBinder(10971): 	at android.widget.Toast.<init>(Toast.java:92)
04-14 22:05:08.305: E/JavaBinder(10971): 	at android.widget.Toast.makeText(Toast.java:265)
04-14 22:05:08.305: E/JavaBinder(10971): 	at com.zsy.frame.sample.jni.control.advance.service.base.MusicSer$MusicBinder.onTransact(MusicSer.java:52)
04-14 22:05:08.305: E/JavaBinder(10971): 	at android.os.Binder.execTransact(Binder.java:404)
04-14 22:05:08.305: E/JavaBinder(10971): 	at dalvik.system.NativeStart.run(Native Method)
*/
