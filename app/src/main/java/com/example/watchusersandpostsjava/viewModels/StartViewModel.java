package com.example.watchusersandpostsjava.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.watchusersandpostsjava.dagger.DaggerStartComponent;
import com.example.watchusersandpostsjava.models.User;
import com.example.watchusersandpostsjava.network.PlaceholderRepository;

import java.util.List;

import javax.inject.Inject;

public class StartViewModel extends ViewModel {

    /**
     * Usual repository layer for working with data
     */
    @Inject
    PlaceholderRepository repository;

    /**
     * List of users that will be observed by fragment
     */
    private LiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public void setUsers(LiveData<List<User>> users) {
        this.users = users;
    }

    public StartViewModel() {
        super();
        DaggerStartComponent.create().inject(this);
        users = repository.getUsers();
    }
}
