package ru.gressor.mytimetable.ui.classes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gressor.mytimetable.databinding.ItemClassesClassBinding
import ru.gressor.mytimetable.entities.Class
import ru.gressor.mytimetable.utils.SkypeLinkListener
import java.text.SimpleDateFormat
import java.util.*

class ClassesRecyclerView(
    private val skypeLinkListener: SkypeLinkListener
) : RecyclerView.Adapter<ClassesRecyclerView.ClassViewHolder>() {
    private val classesList = mutableListOf<Class>()

    fun populate(list: List<Class>) {
        classesList.clear()
        classesList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ClassViewHolder(
            ItemClassesClassBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        holder.bind(classesList[position])
    }

    override fun getItemCount() = classesList.size

    inner class ClassViewHolder(private val binding: ItemClassesClassBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val df = SimpleDateFormat("HH:mm", Locale.getDefault())

        @SuppressLint("SetTextI18n")
        fun bind(theClass: Class) {
            with(binding) {
                classTimepiece.text =
                    "${df.format(theClass.beginning)} - ${df.format(theClass.ending)}"
                classCaption.text = theClass.subject
                classTeacher.text = "Teacher: ${theClass.teacher}"

                backgroundView.alpha = if (theClass.optional) 1f else 0.2f

                if (theClass.skype == "") {
                    callButton.visibility = View.INVISIBLE
                    callButton.isClickable = false
                } else {
                    callButton.visibility = View.VISIBLE
                    callButton.isClickable = true
                    callButton.setOnClickListener {
                        skypeLinkListener.startSkypeLink(theClass.skype)
                    }
                }
            }
        }
    }
}