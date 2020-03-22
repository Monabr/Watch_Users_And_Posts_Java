package com.example.watchusersandpostsjava.viewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.watchusersandpostsjava.adapters.PostAdapter;
import com.example.watchusersandpostsjava.dagger.DaggerStartComponent;
import com.example.watchusersandpostsjava.models.Post;
import com.example.watchusersandpostsjava.network.PlaceholderRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class UserPostsViewModel extends ViewModel {
    private static final String POSTS = "Posts";
    private static final String COMMENTS = "Comments";

    @Inject
    PlaceholderRepository repository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<List<Post>> posts;
    private PostAdapter adapter;

    public PostAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(PostAdapter adapter) {
        this.adapter = adapter;
    }

    public MutableLiveData<List<Post>> getPosts() {
        return posts;
    }

    public void setPosts(MutableLiveData<List<Post>> posts) {
        this.posts = posts;
    }

    public UserPostsViewModel() {
        super();
        DaggerStartComponent.create().inject(this);
        compositeDisposable = new CompositeDisposable();
        posts = new MutableLiveData<>();
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public void onActivityCreated(int userId) {
        compositeDisposable.add(repository.getUserPosts(userId).subscribe(
                posts -> {
                    this.posts.setValue(posts);
                    for (int index = 0; index < posts.size(); index++) {
                        int finalIndex = index;
                        compositeDisposable.add(repository.getPostComments(posts.get(index).getId())
                                .subscribe(
                                        comments -> {
                                            posts.get(finalIndex).setComments(comments);
                                            adapter.notifyItemChanged(finalIndex);
                                        },
                                        throwable -> {
                                            Log.e(COMMENTS, throwable.getLocalizedMessage(), throwable);
                                        }));
                    }
                },
                throwable -> {
                    Log.e(POSTS, throwable.getLocalizedMessage(), throwable);
                }
        ));
    }
}
