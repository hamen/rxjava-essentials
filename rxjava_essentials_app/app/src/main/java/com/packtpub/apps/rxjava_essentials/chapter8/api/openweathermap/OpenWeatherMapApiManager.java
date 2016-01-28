package com.packtpub.apps.rxjava_essentials.chapter8.api.openweathermap;

import com.packtpub.apps.rxjava_essentials.chapter8.api.openweathermap.models.WeatherResponse;

import lombok.Getter;
import retrofit.RestAdapter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OpenWeatherMapApiManager {

    @Getter
    private static OpenWeatherMapApiManager instance = new OpenWeatherMapApiManager();

    private final OpenWeatherMapService mOpenWeatherMapService;

    private OpenWeatherMapApiManager() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org")
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .build();

        mOpenWeatherMapService = restAdapter.create(OpenWeatherMapService.class);
    }

    public Observable<WeatherResponse> getForecastByCity(String city) {
        return mOpenWeatherMapService
                .getForecastByCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
