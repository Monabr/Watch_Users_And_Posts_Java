package com.example.watchusersandpostsjava.network;

import com.example.watchusersandpostsjava.models.Comment;
import com.example.watchusersandpostsjava.models.Post;
import com.example.watchusersandpostsjava.models.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceholderApi {
    @GET("users")
    Call<List<User>> getUsers();

    @GET("posts")
    Single<List<Post>> getUserPosts(@Query("userId") int userId);

    @GET("comments")
    Single<List<Comment>> getPostComments(@Query("postId") int postId);
}
