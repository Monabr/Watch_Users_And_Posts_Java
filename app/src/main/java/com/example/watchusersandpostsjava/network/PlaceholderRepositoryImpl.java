package com.example.watchusersandpostsjava.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.watchusersandpostsjava.models.Comment;
import com.example.watchusersandpostsjava.models.Post;
import com.example.watchusersandpostsjava.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceholderRepositoryImpl implements PlaceholderRepository {
    private PlaceholderApi placeholderApi;

    public @Inject PlaceholderRepositoryImpl(PlaceholderApi placeholderApi) {
        this.placeholderApi = placeholderApi;
    }

    @Override
    public LiveData<List<User>> getUsers() {
        MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();

        placeholderApi.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NotNull Call<List<User>> call, @NotNull Response<List<User>> response) {
               mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<User>> call, @NotNull Throwable t) {

            }
        });

        return mutableLiveData;
    }

    @Override
    public Single<List<Post>> getUserPosts(int userId) {
           return placeholderApi.getUserPosts(userId).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<Comment>> getPostComments(int postId) {
          return placeholderApi.getPostComments(postId).observeOn(AndroidSchedulers.mainThread());
    }
}
