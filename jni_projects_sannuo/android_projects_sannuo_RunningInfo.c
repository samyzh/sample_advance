#include "samy_log.h"
#include "com_zsy_frame_sample_jni_control_android_projects_sannuo_RunningInfo.h"
#include "car_internal_show_R.h"

//notice this two function;is very important;
//but now I not understand
extern int StartRunning(void);
extern car_show_iterm_t tojava_1;

jint JNICALL Java_com_zsy_frame_sample_jni_control_android_projects_sannuo_RunningInfo_StartRunning(
		JNIEnv * env, jobject jobj) {
	StartRunning();
	return 0;
}
jint JNICALL Java_com_zsy_frame_sample_jni_control_android_projects_sannuo_RunningInfo_StopRuning(
		JNIEnv * env, jobject jobj) {
	return 0;
}

jstring JNICALL Java_com_zsy_frame_sample_jni_control_android_projects_sannuo_RunningInfo_GetWelcomInfo(
		JNIEnv * env, jobject jobj) {
	return (*env)->NewStringUTF(env, "Welcom Sannuo");
}

jshort JNICALL Java_com_zsy_frame_sample_jni_control_android_projects_sannuo_RunningInfo_GetRotateSpeed(
		JNIEnv * env, jobject jobj) {
	return (jshort)(tojava_1.speed1);
}

jshort JNICALL Java_com_zsy_frame_sample_jni_control_android_projects_sannuo_RunningInfo_GetVehicleSpeed(
		JNIEnv * env, jobject jobj) {
	return (jshort) tojava_1.speed2;
}

jshort JNICALL Java_com_zsy_frame_sample_jni_control_android_projects_sannuo_RunningInfo_GetWaterTemperature(
		JNIEnv * env, jobject jobj) {
	return (jshort) tojava_1.speed3;
}

jshort JNICALL Java_com_zsy_frame_sample_jni_control_android_projects_sannuo_RunningInfo_GetAirDamper(
		JNIEnv * env, jobject jobj) {
	return (jshort) tojava_1.speed4;
}

jshort JNICALL Java_com_zsy_frame_sample_jni_control_android_projects_sannuo_RunningInfo_GetTirePressure(
		JNIEnv * env, jobject jobj) {
	return (jshort) tojava_1.speed5;
}

jshort JNICALL Java_com_zsy_frame_sample_jni_control_android_projects_sannuo_RunningInfo_GetOilMass(
		JNIEnv * env, jobject jobj) {
	return (jshort) tojava_1.speed6;
}

jshort JNICALL Java_com_zsy_frame_sample_jni_control_android_projects_sannuo_RunningInfo_GetOilWear(
		JNIEnv * env, jobject jobj) {
	return (jshort) tojava_1.speed7;
}

jshort JNICALL Java_com_zsy_frame_sample_jni_control_android_projects_sannuo_RunningInfo_GetRemainingOil(
		JNIEnv * env, jobject jobj) {
	return (jshort) tojava_1.speed8;
}
