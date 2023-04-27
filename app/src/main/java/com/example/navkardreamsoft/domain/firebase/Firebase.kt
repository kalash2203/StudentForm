package com.example.navkardreamsoft.domain.firebase

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.navkardreamsoft.data.Student
import com.example.navkardreamsoft.presentation.activity.RecordActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import java.util.*

class Firebase {

    private var db = FirebaseFirestore.getInstance()
    private  var database= Firebase.firestore

    fun getRecords(activity: RecordActivity){
        db.collection("students")
            .get()
            .addOnSuccessListener { result ->
               val students = result.toObjects<Student>()
                activity.setData(students)
            }
            .addOnFailureListener { exception ->
                Log.e("MainActivity", "Error getting documents: ", exception)
            }
    }

    // Function to save a student object to Firebase database
    fun saveStudentToFirebase(student: Student,context: Context) {
        // Get a reference to the "students" node in the Firebase database
        val studentsRef = database.collection("students")

        // Generate a unique ID for the new student object
        student.id = UUID.randomUUID().toString()

        // Save the new student object to the Firebase database
        studentsRef.add(student)
            .addOnSuccessListener {
                // Show a success message
                Toast.makeText(context, "Student saved successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                // Show an error message
                Log.e("Firebase", "Error saving student: ${exception.message}")
                Toast.makeText(context, "Error saving student", Toast.LENGTH_SHORT).show()
            }

    }
}