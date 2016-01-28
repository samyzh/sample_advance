#include "samy_log.h"
#include "com_zsy_frame_sample_jni_control_android_encrypt_signature_SignatureAct.h"

JNIEXPORT jstring JNICALL Java_com_zsy_frame_sample_jni_control_android_encrypt_signature_SignatureAct_get_1apk_1signature(
		JNIEnv *env, jobject thiz) {
	// 获得 Context 类
	jclass native_clazz = (*env)->GetObjectClass(env, thiz);

	// 得到 getPackageManager 方法的 ID
	jmethodID methodID_func = (*env)->GetMethodID(env, native_clazz,
			"getPackageManager", "()Landroid/content/pm/PackageManager;");

	// 获得应用包的管理器
	jobject package_manager = (*env)->CallObjectMethod(env, thiz,
			methodID_func);

	// 获得 PackageManager 类
	jclass pm_clazz = (*env)->GetObjectClass(env, package_manager);

	// 得到 getPackageInfo 方法的 ID
	jmethodID methodID_pm = (*env)->GetMethodID(env, pm_clazz, "getPackageInfo",
			"(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");

	// 获得应用包的信息
	jobject package_info = (*env)->CallObjectMethod(env, package_manager,
			methodID_pm, (*env)->NewStringUTF(env, "com.zsy.frame.sample.jni"), 64);

	// 获得 PackageInfo 类
	jclass pi_clazz = (*env)->GetObjectClass(env, package_info);

	// 获得签名数组属性的 ID
	jfieldID fieldID_signatures = (*env)->GetFieldID(env, pi_clazz,
			"signatures", "[Landroid/content/pm/Signature;");

	// 得到签名数组，待修改
	jobjectArray signatures = (*env)->GetObjectField(env, package_info,
			fieldID_signatures);

	// 得到签名
	jobject signature = (*env)->GetObjectArrayElement(env, signatures, 0);

	// 获得 Signature 类，待修改
	jclass s_clazz = (*env)->GetObjectClass(env, signature);

	// 得到 hashCode 方法的 ID
	jmethodID methodID_hc = (*env)->GetMethodID(env, s_clazz, "hashCode",
			"()I");

	// 获得应用包的管理器，待修改
	int hash_code = (*env)->CallIntMethod(env, signature, methodID_hc);

	char str[32];

	sprintf(str, "hash_code : %u", hash_code);

	return (*env)->NewStringUTF(env, str);
}
