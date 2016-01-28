LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)


LOCAL_CFLAGS := -DANDROID_NDK \
                -DDISABLE_IMPORTGL
                
LOCAL_SRC_FILES := nanojpeg.c mem_utils.c bitmap.c bicubic_resize.c filter.c transform.c colour_space.c matrix.c blur.c android_multimedia_image_filter_PhotoProcessing.c
#LOCAL_SRC_FILES := nanojpeg.c mem_utils.c bitmap.c bicubic_resize.c filter.c transform.c colour_space.c matrix.c blur.c photo_processing.c
LOCAL_LDLIBS    := -lm -llog
LOCAL_MODULE    := android_multimedia_image_filter_PhotoProcessing

include $(BUILD_SHARED_LIBRARY)