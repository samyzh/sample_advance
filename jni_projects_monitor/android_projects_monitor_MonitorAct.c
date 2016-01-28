#include "samy_log.h"
#include "com_zsy_frame_sample_jni_control_android_projects_monitor_MonitorAct.h"

// c语言中的随机数
int getpressure() {
	return rand();
}
JNIEXPORT jint JNICALL Java_com_zsy_frame_sample_jni_control_android_projects_monitor_MonitorAct_getPressure(
		JNIEnv * env, jobject obj) {
	return getpressure();
}
