package com.example.watchusersandpostsjava.dagger.modules;

import com.example.watchusersandpostsjava.network.PlaceholderApi;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class PlaceholderApiModule {

    @Provides
    @NonNull
    PlaceholderApi providePlaceholderApi() {
        String BASE_SERVER = "https://jsonplaceholder.typicode.com/";
        return new Retrofit.Builder()
                .baseUrl(BASE_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(PlaceholderApi.class);
    }
}
