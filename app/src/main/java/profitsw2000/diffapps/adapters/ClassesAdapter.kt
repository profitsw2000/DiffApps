package profitsw2000.diffapps.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import profitsw2000.diffapps.R
import profitsw2000.diffapps.databinding.ClassesRecyclerviewItemBinding
import profitsw2000.diffapps.model.Lesson

class ClassesAdapter (
    val data: List<Lesson>
) : RecyclerView.Adapter<ClassesAdapter.ViewHolder>() {

    private lateinit var binding: ClassesRecyclerviewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ClassesRecyclerviewItemBinding.inflate(
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
        fun bind(lesson: Lesson) {
            val lessonTime = "${lesson.startHour}.${getMinutesString(lesson.startMinute)} - ${lesson.endHour}.${getMinutesString(lesson.endMinute)}"
            val resId = getImageResourceId(lesson)
            with(binding) {
                disciplineNameTextView.text = lesson.name
                lessonTimeTextView.text = lessonTime
                classesRecyclerViewItemImageView.setImageResource(resId)
                if (!lesson.isOnline) skypeLinkConstraintLayout.visibility = View.GONE
            }
        }
    }

    private fun getImageResourceId(lesson: Lesson): Int {
        val resId = when (lesson.name) {
            "History" -> R.drawable.bow_and_arrow
            "Literature" -> R.drawable.literature_icon
            "Physics" -> R.drawable.physics_icon
            "English" -> R.drawable.english_icon
            "Mathematics" -> R.drawable.math_icon
            "Physical education" -> R.drawable.physical_education_icon
            else -> R.drawable.bow_and_arrow
        }
        return resId
    }

    private fun getMinutesString(minute: Int) : String {
        return if (minute < 10) "0$minute"
        else minute.toString()
    }

}