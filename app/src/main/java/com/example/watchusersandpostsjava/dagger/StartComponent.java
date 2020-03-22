package com.example.watchusersandpostsjava.dagger;

import com.example.watchusersandpostsjava.dagger.modules.PlaceholderApiModule;
import com.example.watchusersandpostsjava.dagger.modules.PlaceholderRepositoryImplModule;
import com.example.watchusersandpostsjava.viewModels.StartViewModel;
import com.example.watchusersandpostsjava.viewModels.UserPostsViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PlaceholderApiModule.class, PlaceholderRepositoryImplModule.class})
public interface StartComponent {

    void inject(StartViewModel startViewModel);

    void inject(UserPostsViewModel userPostsViewModel);
}
