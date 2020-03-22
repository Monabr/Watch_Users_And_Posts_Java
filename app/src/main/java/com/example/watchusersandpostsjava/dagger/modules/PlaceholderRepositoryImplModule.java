package com.example.watchusersandpostsjava.dagger.modules;

import com.example.watchusersandpostsjava.network.PlaceholderRepository;
import com.example.watchusersandpostsjava.network.PlaceholderRepositoryImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PlaceholderRepositoryImplModule {

    @Binds
    abstract PlaceholderRepository bindsPlaceholderRepositoryImpl(PlaceholderRepositoryImpl placeholderRepositoryImpl);
}
