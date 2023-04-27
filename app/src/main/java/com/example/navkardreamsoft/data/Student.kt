package com.example.navkardreamsoft.data

data class Student(

    var id: String="",
    val name: String="",
    val dateOfBirth:String="",
    val gender:String="",
    val address:String="",
    val passingYear: String="",
    val contactNumber: String="",
    val email: String="",
    val registrationFees: Double=0.00,
    val collegeFees: Double=0.00,
    val examFees: Double=0.00,
    val totalFees: Double=0.00

)
