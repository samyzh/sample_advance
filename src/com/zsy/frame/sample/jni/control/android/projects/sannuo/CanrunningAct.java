package com.zsy.frame.sample.jni.control.android.projects.sannuo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.zsy.frame.sample.jni.R;

public class CanrunningAct extends Activity {
	TextView t1;
	TextView t2;
	TextView t3;
	TextView t4;
	TextView t5;
	TextView t6;
	TextView t7;
	TextView t8;
	RunningInfo Infoobj;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_projects_sannuo_canrunning);

		Infoobj = new RunningInfo();
		// Infoobj.GetWelcomInfo();
		// System.out.println(Infoobj.GetWelcomInfo());

		t1 = (TextView) this.findViewById(R.id.a1);
		t2 = (TextView) this.findViewById(R.id.a2);
		t3 = (TextView) this.findViewById(R.id.a3);
		t4 = (TextView) this.findViewById(R.id.a4);
		t5 = (TextView) this.findViewById(R.id.a5);
		t6 = (TextView) this.findViewById(R.id.a6);
		t7 = (TextView) this.findViewById(R.id.a7);
		t8 = (TextView) this.findViewById(R.id.a8);
		
		Timer timer = new Timer();
		timer.schedule(task, 1000, 2000);
	}

	TimerTask task = new TimerTask() {
		public void run() {
			Message message = new Message();
			message.arg1 = 888;
			handler.sendMessage(message);
		}
	};
	
	
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
				case 888:
					updateView();
					break;
			}
			super.handleMessage(msg);
		}

	};

	private void updateView() {
		// System.out.println("Infoobj.GetRotateSpeed() "+ Infoobj.GetRotateSpeed());
		// System.out.println("Infoobj.GetVehicleSpeed() "+ Infoobj.GetVehicleSpeed());
		// System.out.println("Infoobj.GetWaterTemperature() "+ Infoobj.GetWaterTemperature());
		// System.out.println("Infoobj.GetAirDamper() "+ Infoobj.GetAirDamper());
		// System.out.println("Infoobj.GetTirePressure() "+ Infoobj.GetTirePressure());
		// System.out.println("Infoobj.GetOilMass() "+Infoobj.GetOilMass() );
		// System.out.println("Infoobj.GetOilWear() "+Infoobj.GetOilWear() );
		// System.out.println("Infoobj.GetRemainingOil() "+ Infoobj.GetRemainingOil());

		t1.setText(Infoobj.GetRotateSpeed() + "");
		t2.setText(Infoobj.GetVehicleSpeed() + "");
		t3.setText(Infoobj.GetWaterTemperature() + "");
		t4.setText(Infoobj.GetAirDamper() + "");
		t5.setText(Infoobj.GetTirePressure() + "");
		t6.setText(Infoobj.GetOilMass() + "");
		t7.setText(Infoobj.GetOilWear() + "");
		t8.setText(Infoobj.GetRemainingOil() + "");

		// System.out.println("update");
	}
}