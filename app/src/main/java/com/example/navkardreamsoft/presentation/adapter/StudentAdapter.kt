package com.example.navkardreamsoft.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navkardreamsoft.R
import com.example.navkardreamsoft.data.Student


class StudentAdapter(val context : Context) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var students: List<Student> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position],context)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    fun setData(students: List<Student>) {
        this.students = students
        notifyDataSetChanged()
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(student: Student, context: Context) {
            itemView.findViewById<TextView>(R.id.nameTextView).text =  context.getString(R.string.name) + " - " + student.name
            itemView.findViewById<TextView>(R.id.genderTextView).text = context.getString(R.string.gender) + " - " + student.gender
            itemView.findViewById<TextView>(R.id.addressTextView).text = context.getString(R.string.address) + " - " + student.address
            itemView.findViewById<TextView>(R.id.passingYearTextView).text =  context.getString(R.string.passing_year) +  " - " + student.passingYear
            itemView.findViewById<TextView>(R.id.contactTextView).text =  context.getString(R.string.mobile_number) +  " - " + student.contactNumber
            itemView.findViewById<TextView>(R.id.emailTextView).text =  context.getString(R.string.email) +  " - " + student.email
            itemView.findViewById<TextView>(R.id.registrationFeesTextView).text =  context.getString(
                R.string.registration_fees
            ) +  " - " + student.registrationFees.toString()
            itemView.findViewById<TextView>(R.id.collegeFeesTextView).text =  context.getString(R.string.college_fees) +  " - " + student.collegeFees.toString()
            itemView.findViewById<TextView>(R.id.examFeesTextView).text =  context.getString(R.string.exam_fees) +  " - " + student.examFees.toString()
            itemView.findViewById<TextView>(R.id.totalFeesTextView).text =  context.getString(R.string.total_fees) +  " - " + student.totalFees.toString()
        }
    }
}