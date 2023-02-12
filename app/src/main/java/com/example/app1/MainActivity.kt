package com.example.app1

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var text:TextView?=null
    private var ageInMinutes:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ageInMinutes=findViewById(R.id.ageInMinutes)
        val clk:Button=findViewById(R.id.btnDatepicker)
        text=findViewById(R.id.selectedDate)
        clk.setOnClickListener {
            clkDatePicker()
            Toast.makeText(this,"clk pressed",Toast.LENGTH_LONG).show()
        }
    }

    fun clkDatePicker(){
        val myCalender= Calendar.getInstance()
        val year=myCalender.get(Calendar.YEAR)
        val month=myCalender.get(Calendar.MONTH)
        val day=myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {view,selectedYear,selectedMonth,selectedDayOfMonth->
                 Toast.makeText(this,
                     "year was $selectedYear,month was ${selectedMonth+1}" +
                         ", day of month was $selectedDayOfMonth",
                     Toast.LENGTH_LONG).show()
                 val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                 text?.setText(selectedDate)
                 val sdf=SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                 val theDate=sdf.parse(selectedDate)
                 val selectedDateInMinutes=theDate.time/60000
                 val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                 val currentDateInMinutes=currentDate.time/60000
                 val differenceInMinutes=currentDateInMinutes-selectedDateInMinutes
                 ageInMinutes?.text=differenceInMinutes.toString()

            },
            year,
            month,
            day
            ).show()

    }
}