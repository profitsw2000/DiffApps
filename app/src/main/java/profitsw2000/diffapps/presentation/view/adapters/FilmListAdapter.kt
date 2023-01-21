package profitsw2000.diffapps.presentation.view.adapters

import Docs
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import profitsw2000.diffapps.R
import profitsw2000.diffapps.databinding.FilmListItemViewBinding

class FilmListAdapter : RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {

    private var data: ArrayList<Docs> = arrayListOf()
    private lateinit var binding: FilmListItemViewBinding

    fun setData(data: ArrayList<Docs>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FilmListItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(docs: Docs, position: Int) {
            with(binding){
                filmPosterImageView.setImageResource(R.drawable.poster)
                filmTitleTextView.text = docs.name
                filmYearTextView.text = docs.year.toString()
                filmRatingTextView.text = docs.rating.kp.toString()
            }
        }
    }
}