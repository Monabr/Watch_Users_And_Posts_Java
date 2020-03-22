package com.example.watchusersandpostsjava.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchusersandpostsjava.R;
import com.example.watchusersandpostsjava.databinding.ItemUserBinding;
import com.example.watchusersandpostsjava.models.User;
import com.example.watchusersandpostsjava.views.MainContainerActivity;
import com.example.watchusersandpostsjava.views.UserPostsFragment;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> users;

    /**
     * For manipulation with view avoiding {@link View#findViewById(int)}
     */
    private ItemUserBinding binding;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(User user) {
            binding.itemUserTvName.setText(user.getName());
            binding.itemUserTvUsername.setText(user.getUsername());
            binding.itemUserTvEmail.setText(user.getEmail());
            binding.itemUserTvAddress.setText((user.getAddress().getCity() + ", " + user.getAddress().getStreet() + ", " + user.getAddress().getSuite()));

            if (!itemView.hasOnClickListeners()) {
                itemView.setOnClickListener(v -> {
                    Bundle args = new Bundle();
                    args.putInt(UserPostsFragment.USER_ID, user.getId());
                    Navigation.findNavController((MainContainerActivity) (itemView.getContext()), R.id.container).navigate(R.id.action_startFragment_to_userPostsFragment, args);
                });
            }
        }
    }
}
