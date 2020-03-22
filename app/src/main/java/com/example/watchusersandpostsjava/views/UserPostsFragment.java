package com.example.watchusersandpostsjava.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.watchusersandpostsjava.R;
import com.example.watchusersandpostsjava.adapters.PostAdapter;
import com.example.watchusersandpostsjava.databinding.FragmentUserPostsBinding;
import com.example.watchusersandpostsjava.viewModels.UserPostsViewModel;

public class UserPostsFragment extends Fragment {

    public static final String USER_ID = "userId";
    private static final String USER_POSTS_FRAGMENT = "UserPostsFragment";

    /**
     * Using to get posts and comments for them
     */
    private UserPostsViewModel viewModel;

    /**
     * For manipulation with view avoiding {@link View#findViewById(int)}
     */
    private FragmentUserPostsBinding binding;

    /**
     * Local link for {@link UserPostsFragment#viewModel} to make {@link PostAdapter#notifyItemChanged(int)} when comments will come for a post
     */
    private PostAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UserPostsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserPostsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            viewModel.onActivityCreated(getArguments().getInt(USER_ID));
        } else {
            Log.e(USER_POSTS_FRAGMENT, getString(R.string.User_posts_fragment_no_id));
        }
        adapter = new PostAdapter();
        binding.fragmentUserPostsRvPostList.setAdapter(adapter);
        viewModel.setAdapter(adapter);
        viewModel.getPosts().observe(getViewLifecycleOwner(), posts -> {
            adapter.setPosts(posts);
            binding.fragmentUserPostsRvPostList.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.fragmentUserPostsPbPosts.setVisibility(View.GONE);
        });
    }

    public static UserPostsFragment newInstance() {
        return new UserPostsFragment();
    }
}
