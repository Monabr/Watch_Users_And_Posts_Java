package com.example.watchusersandpostsjava.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.watchusersandpostsjava.adapters.UserAdapter;
import com.example.watchusersandpostsjava.databinding.FragmentStartBinding;
import com.example.watchusersandpostsjava.viewModels.StartViewModel;

public class StartFragment extends Fragment {

    /**
     * Using to get list of users
     */
    private StartViewModel viewModel;

    /**
     * For manipulation with view avoiding {@link View#findViewById(int)}
     */
    private FragmentStartBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(StartViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getUsers().observe(getViewLifecycleOwner(), users -> {
            binding.fragmentStartRvUsersList.setAdapter(new UserAdapter(users));
            binding.fragmentStartRvUsersList.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.fragmentStartPbLoad.setVisibility(View.GONE);
        });
    }

    public static StartFragment newInstance() {

        Bundle args = new Bundle();

        StartFragment fragment = new StartFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

