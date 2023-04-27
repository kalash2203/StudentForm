
package com.example.navkardreamsoft.presentation.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.navkardreamsoft.data.Student
import com.example.navkardreamsoft.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var dob =""

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.btnRecord.setOnClickListener {
                val intent = Intent (this, RecordActivity::class.java)
                startActivity(intent)

            }

            binding.groupBirthDate.setOnClickListener {
                // Create a Calendar instance with the current date
                val calendar = Calendar.getInstance()

                // Create a DatePickerDialog with the current date and an OnDateSetListener
                val datePickerDialog = DatePickerDialog(
                    this,
                    { _, year, monthOfYear, dayOfMonth ->
                        // Update the TextView with the selected date
                        binding.edtBirthDate.setText("$dayOfMonth/${monthOfYear + 1}/$year")
                        dob = "${monthOfYear + 1}-$dayOfMonth-$year"
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )

                // Show the DatePickerDialog
                datePickerDialog.show()
            }

            // Set a click listener for the save button
            binding.btnSave.setOnClickListener {

                var gender=""
                val name = binding.edtName.text.toString().trim()
                val address = binding.edtAddress.text.toString().trim()
                val passingYear = binding.edtPassingYear.text.toString().trim()
                val contactNumber = binding.edtMobileNo.text.toString().trim()
                val email = binding.edtEmail.text.toString().trim()
                val regFeesString = binding.edtRegistrationFees.text.toString().trim()
                val collegeFeesString = binding.edtCollegeFees.text.toString().trim()
                val examFeesString = binding.edtExamFees.text.toString().trim()


                if(binding.male.isChecked)
                {
                    gender="male"
                }
                else if(binding.female.isChecked)
                {
                    gender="female"
                }





                // Validate the input values
                if (name.isEmpty()) {
                    Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Validate the input values
                if (binding.male.isChecked==false && binding.female.isChecked==false) {
                    Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Validate the input values
                if (address.isEmpty()) {
                    Toast.makeText(this, "Please enter address", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Validate the input values
                if (passingYear.isEmpty()) {
                    Toast.makeText(this, "Please enter passing year", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (!isValidContactNumber(contactNumber)) {
                    Toast.makeText(this, "Please enter a valid contact number", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (!isValidEmail(email)) {
                    Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (!isValidNumericValue(regFeesString) || !isValidNumericValue(collegeFeesString) || !isValidNumericValue(examFeesString)) {
                    Toast.makeText(this, "Please enter valid numeric values for fees", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Convert the fees strings to numeric values
                val regFees = regFeesString.toDouble()
                val collegeFees = collegeFeesString.toDouble()
                val examFees = examFeesString.toDouble()

                // Calculate the total fees

                val totalFees = regFees + collegeFees + examFees

                // Update the UI with the total fees
                binding.edtTotalFees.setText(String.format("%.2f", totalFees))

                // Create a new student object with the input values
                val newStudent = Student("",name, dob,gender,address,passingYear,
                    contactNumber, email, regFees, collegeFees, examFees, totalFees)

                val firebase = com.example.navkardreamsoft.domain.firebase.Firebase()
                // Save the new student object to Firebase database
                firebase.saveStudentToFirebase(newStudent,this)
            }
        }

        // Function to validate a contact number (must be 10 digits, alphanumeric not allowed)
        private fun isValidContactNumber(contactNumber: String): Boolean {
            val regex = Regex("^[0-9]{10}$")
            return regex.matches(contactNumber)
        }
        // Function to validate an email address (must be in the format of "username@domain.com")
        private fun isValidEmail(email: String): Boolean {
            val regex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
            return regex.matches(email)
        }

        // Function to check if a string is a valid numeric value
        private fun isValidNumericValue(value: String): Boolean {
            return value.toDoubleOrNull() != null
        }


    }

