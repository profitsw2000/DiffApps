package profitsw2000.diffapps.presentation.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import profitsw2000.diffapps.databinding.RedditPostsRecyclerviewItemBinding
import profitsw2000.diffapps.model.Post

class RedditPostsPagedListAdapter : PagedListAdapter<Post, RedditPostsPagedListAdapter.PostItemViewHolder>(PostDiffCallback()) {

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        return PostItemViewHolder(RedditPostsRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    class PostItemViewHolder (val binding: RedditPostsRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post?, position: Int) {
            if (post != null) {
                with(binding) {
                    postTitleTextView.text = post.title
                    commentsNumberTextView.text = post.numComments.toString()
                    likesNumberTextView.text = post.score.toString()
                }
            }
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
}