package com.example.watchusersandpostsjava.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchusersandpostsjava.R;
import com.example.watchusersandpostsjava.databinding.ItemPostBinding;
import com.example.watchusersandpostsjava.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Post> posts;

    /**
     * For manipulation with view avoiding {@link View#findViewById(int)}
     */
    private ItemPostBinding binding;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Post post) {
            binding.itemPostTitle.setText(post.getTitle());
            binding.itemPostBody.setText(post.getBody());
            if (!(post.getComments() == null || post.getComments().isEmpty())) {
                binding.itemPostPbComments.setVisibility(View.GONE);
                binding.itemPostCommentsCount.setVisibility(View.VISIBLE);
                binding.itemPostCommentsCount.setText(String.valueOf(post.getComments().size()));

                binding.itemPostRvCommentList.setAdapter(new CommentAdapter(post.getComments()));
                binding.itemPostRvCommentList.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
                binding.itemPostIvExpandArrow.setVisibility(View.VISIBLE);

                if (!itemView.hasOnClickListeners()) {
                    itemView.setOnClickListener(v -> {
                        switch (v.findViewById(R.id.item_post_rv_comment_list).getVisibility()) {
                            case View.GONE:
                                v.findViewById(R.id.item_post_rv_comment_list).setVisibility(View.VISIBLE);
                                v.findViewById(R.id.item_post_iv_expand_arrow).animate().rotation(180f).setDuration(300).start();
                                break;
                            case View.VISIBLE:
                                v.findViewById(R.id.item_post_rv_comment_list).setVisibility(View.GONE);
                                v.findViewById(R.id.item_post_iv_expand_arrow).animate().rotation(0f).setDuration(300).start();
                                break;
                            case View.INVISIBLE:
                                break;
                        }
                    });
                }
            }
        }
    }
}
