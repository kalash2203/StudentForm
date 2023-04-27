package com.example.navkardreamsoft.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navkardreamsoft.R
import com.example.navkardreamsoft.presentation.adapter.StudentAdapter
import com.example.navkardreamsoft.data.Student
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase


class RecordActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val firebase = com.example.navkardreamsoft.domain.firebase.Firebase()
        firebase.getRecords(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        studentAdapter = StudentAdapter(this)
        recyclerView.adapter = studentAdapter
    }


    fun setData(list:List<Student>){
        studentAdapter.setData(list)
    }
}
