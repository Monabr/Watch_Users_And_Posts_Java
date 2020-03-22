package com.example.watchusersandpostsjava.network;

import androidx.lifecycle.LiveData;

import com.example.watchusersandpostsjava.models.Comment;
import com.example.watchusersandpostsjava.models.Post;
import com.example.watchusersandpostsjava.models.User;

import java.util.List;

import io.reactivex.Single;

public interface PlaceholderRepository {
    LiveData<List<User>> getUsers();
    Single<List<Post>> getUserPosts(int userId);
    Single<List<Comment>> getPostComments(int postId);
}
