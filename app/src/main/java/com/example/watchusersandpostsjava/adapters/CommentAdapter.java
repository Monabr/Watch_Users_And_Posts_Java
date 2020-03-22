package com.example.watchusersandpostsjava.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchusersandpostsjava.databinding.ItemCommentBinding;
import com.example.watchusersandpostsjava.databinding.ItemPostBinding;
import com.example.watchusersandpostsjava.models.Comment;
import com.example.watchusersandpostsjava.models.Post;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Comment> comments;

    private ItemCommentBinding binding;

    public CommentAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Comment comment) {
            binding.itemCommentTvEmail.setText(comment.getEmail());
            binding.itemCommentTvBody.setText(comment.getBody());
        }
    }
}
