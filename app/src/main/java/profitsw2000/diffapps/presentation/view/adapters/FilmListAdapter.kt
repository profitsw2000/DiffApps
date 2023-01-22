package profitsw2000.diffapps.presentation.view.adapters

import profitsw2000.diffapps.entity.topfilms.Docs
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import profitsw2000.diffapps.R
import profitsw2000.diffapps.databinding.FilmListItemViewBinding

class FilmListAdapter (
    val data: ArrayList<Docs>,
    val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {

    private lateinit var binding: FilmListItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FilmListItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(docs: Docs) {
            with(binding){
                filmPosterImageView.setImageResource(R.drawable.poster)
                filmTitleTextView.text = docs.name
                filmYearTextView.text = docs.year.toString()
                filmRatingTextView.text = docs.rating.kp.toString()
                Picasso.get().load(docs.poster.url).into(filmPosterImageView)
                filmPosterImageView.setOnClickListener {
                    onItemClickListener.onItemClick(docs.id)
                }
            }
        }
    }
}