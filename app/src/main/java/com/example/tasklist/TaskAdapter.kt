package com.example.tasklist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(private val tasks: MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )
    }

    // helper function to add a task into the task list
    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

    // helper function to delete tasks from task list
    fun deleteFinishedTasks(){
        tasks.removeAll{ task ->
            task.isChecked
        }
        // update UI with updated task list
        notifyDataSetChanged()
    }

    // uses flags to put a line through selected tasks
    private fun toggleCrossOff(tvTaskTitle: TextView, isChecked: Boolean){
        if(isChecked) {
            tvTaskTitle.paintFlags = tvTaskTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else{
            tvTaskTitle.paintFlags = tvTaskTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    // set values to task object
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.itemView.apply {
            tvTaskTitle.text = task.title
            cbCompleted.isChecked = task.isChecked
            toggleCrossOff(tvTaskTitle, task.isChecked)
            cbCompleted.setOnCheckedChangeListener { _, isChecked ->
                toggleCrossOff(tvTaskTitle, isChecked)
                task.isChecked = !task.isChecked
            }
        }
    }

    // size of list
    override fun getItemCount(): Int {
        return tasks.size
    }
}