package com.packtpub.apps.rxjava_essentials;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

public class App extends Application {

    public static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        }
        Timber.tag("RXJAVA_ESSENTIALS");

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .showImageOnFail(R.drawable.ic_launcher)
            .showImageOnLoading(R.drawable.ic_launcher)
            .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
            .defaultDisplayImageOptions(defaultOptions)
            .build();
        ImageLoader.getInstance().init(config);
    }
}
