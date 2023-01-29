package profitsw2000.diffapps.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import profitsw2000.diffapps.R
import profitsw2000.diffapps.databinding.HomeworkRecyclerviewItemBinding
import profitsw2000.diffapps.model.Homework

class HomeworkAdapter (
    val data: List<Homework>
) : RecyclerView.Adapter<HomeworkAdapter.ViewHolder>() {

    private lateinit var binding: HomeworkRecyclerviewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = HomeworkRecyclerviewItemBinding.inflate(
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
        @SuppressLint("ResourceAsColor")
        fun bind(homework: Homework) {
            val remainTime = "${homework.daysLeft} days left"
            val resId = when(homework.disciplineName) {
                "History" -> R.drawable.bow_and_arrow
                "Literature" -> R.drawable.literature_icon
                "Physics" -> R.drawable.physics_icon
                "English" -> R.drawable.english_icon
                "Mathematics" -> R.drawable.math_icon
                "Physical education" -> R.drawable.physical_education_icon
                else -> R.drawable.bow_and_arrow
            }
            with(binding) {
                disciplineNameTextView.text = homework.disciplineName
                lesonTimeTextView.text = remainTime
                if (homework.daysLeft < 3){
                    lesonTimeTextView.setTextColor(Color.RED)
                    lessonTimeImageView.setImageResource(R.drawable.clock_icon_red)
                } else {
                    lessonTimeImageView.setImageResource(R.drawable.clock_icon)
                }
                classesRecyclerViewItemImageView.setImageResource(resId)
                lessonHomeworkTextView.text = homework.task
            }
        }
    }

}