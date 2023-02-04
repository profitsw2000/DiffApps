package profitsw2000.diffapps.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import profitsw2000.diffapps.databinding.RedditPostsRecyclerviewItemBinding
import profitsw2000.diffapps.model.Post

class RedditPostsPagedListAdapter : PagingDataAdapter<Post, RecyclerView.ViewHolder>(PostDiffCallback()) {

    private lateinit var binding: RedditPostsRecyclerviewItemBinding

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostItemViewHolder).bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = RedditPostsRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostItemViewHolder(binding.root)
    }

    inner class PostItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        fun bind(post: Post?) {
            with(binding) {
                postTitleTextView.text = post?.title
                commentsNumberTextView.text = post?.numComments.toString()
                likesNumberTextView.text = post?.score.toString()
            }
        }
    }
}