package com.example.tasklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create list that will hold entered tasks
        taskAdapter = TaskAdapter(mutableListOf())
        rvTaskList.adapter = taskAdapter
        rvTaskList.layoutManager = LinearLayoutManager(this)

        // add entered task into list when add button is clicked
        btnAddTask.setOnClickListener {
            val taskTitle = etTaskTitle.text.toString()
            if(taskTitle.isNotEmpty()){
                val task = Task(taskTitle)
                taskAdapter.addTask(task)
                etTaskTitle.text.clear()
            }
        }

        // clear list when delete button is clicked
        btnDeleteTask.setOnClickListener {
            taskAdapter.deleteFinishedTasks()
        }
    }
}