package profitsw2000.diffapps.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import profitsw2000.diffapps.OnItemClickListener
import profitsw2000.diffapps.R
import profitsw2000.diffapps.databinding.ClassesBigRecyclerviewBigItemBinding
import profitsw2000.diffapps.databinding.ClassesBigRecyclerviewItemBinding
import profitsw2000.diffapps.databinding.ClassesBigRecyclerviewSkypeItemBinding
import profitsw2000.diffapps.model.Lesson

private const val TYPE_BIG = 1
private const val TYPE_NORMAL = 2
private const val TYPE_SKYPE = 3

class ClassesBigAdapter (
    private val data: List<Lesson>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ClassesBigAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            TYPE_BIG -> {
                val binding = ClassesBigRecyclerviewBigItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                BigViewHolder(binding.root)
            }
            TYPE_SKYPE -> {
                val binding = ClassesBigRecyclerviewSkypeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                SkypeViewHolder(binding.root)
            }
            TYPE_NORMAL -> {
                val binding = ClassesBigRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                NormalViewHolder(binding.root)
            }
            else-> {
                val binding = ClassesBigRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                NormalViewHolder(binding.root)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].description != "") TYPE_BIG
        else {
            if (data[position].isOnline) TYPE_SKYPE
            else TYPE_NORMAL
        }
    }

    abstract class BaseViewHolder(view:View):RecyclerView.ViewHolder(view){
        abstract fun bind(lesson: Lesson, position: Int)
    }

    inner class BigViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(lesson: Lesson, position: Int) {
            val lessonTime = "${lesson.startHour}.${getMinutesString(lesson.startMinute)} - ${lesson.endHour}.${getMinutesString(lesson.endMinute)}"
            val resId = getImageResourceId(lesson)
            ClassesBigRecyclerviewBigItemBinding.bind(itemView).apply {
                setLeftItemView(view1, view2, dotImageView, position)
                lessonTimeTextView.text = lessonTime
                lessonCardLayout.classesRecyclerViewItemImageView.setImageResource(resId)
                lessonCardLayout.disciplineNameTextView.text = lesson.name
                lessonCardLayout.teacherTextView.text = lesson.teacher
                lessonCardLayout.lessonDescriptionTextView.text = lesson.description
            }
        }
    }

    inner class SkypeViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(lesson: Lesson, position: Int) {
            val lessonTime = "${lesson.startHour}.${getMinutesString(lesson.startMinute)} - ${lesson.endHour}.${getMinutesString(lesson.endMinute)}"
            val resId = getImageResourceId(lesson)

            ClassesBigRecyclerviewSkypeItemBinding.bind(itemView).apply {
                setLeftItemView(view1, view2, dotImageView, position)
                lessonTimeTextView.text = lessonTime
                lessonCardLayout.classesRecyclerViewItemImageView.setImageResource(resId)
                lessonCardLayout.disciplineNameTextView.text = lesson.name
                lessonCardLayout.teacherTextView.text = lesson.teacher
                lessonCardLayout.skypeLinkConstraintLayout.setOnClickListener {
                    onItemClickListener.onItemClick()
                }
            }
        }
    }

    inner class NormalViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(lesson: Lesson, position: Int) {
            val lessonTime = "${lesson.startHour}.${getMinutesString(lesson.startMinute)} - ${lesson.endHour}.${getMinutesString(lesson.endMinute)}"
            val resId = getImageResourceId(lesson)
            ClassesBigRecyclerviewItemBinding.bind(itemView).apply {
                setLeftItemView(view1, view2, dotImageView, position)
                lessonTimeTextView.text = lessonTime
                lessonCardLayout.classesRecyclerViewItemImageView.setImageResource(resId)
                lessonCardLayout.disciplineNameTextView.text = lesson.name
                lessonCardLayout.teacherTextView.text = lesson.teacher
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

    private fun setLeftItemView(view1: View, view2: View, view3: ImageView, position: Int) {
        if (position == 0) {
            view1.visibility = View.GONE
            view2.visibility = View.VISIBLE
            view3.setImageResource(R.drawable.big_circle)
        } else {
            view1.visibility = View.VISIBLE
            view2.visibility = View.GONE
            view3.setImageResource(R.drawable.circle)
        }
    }

}