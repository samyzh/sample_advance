LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_SRC_FILES := bitstream.c fft.c id3tag.c mpglib_interface.c presets.c  quantize.c   reservoir.c tables.c  util.c  VbrTag.c encoder.c  gain_analysis.c lame.c  newmdct.c   psymodel.c quantize_pvt.c set_get.c  takehiro.c vbrquantize.c version.c android_multimedia_lame_FormatConversionAct.c 
#LOCAL_SRC_FILES :=android_multimedia_lame_FormatConversionAct.c
LOCAL_MODULE    :=android_multimedia_lame_FormatConversionAct
#liblog.so libGLESv2.so
LOCAL_LDLIBS += -llog
include $(BUILD_SHARED_LIBRARY)