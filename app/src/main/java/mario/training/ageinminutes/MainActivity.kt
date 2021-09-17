package mario.training.ageinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //this is to solve the issue of not finding the button and needs to be declared 1st
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        //this previous line

        btnDatePicker.setOnClickListener{ view ->
            clickDatePicker(view)

        }
    }


    @SuppressLint("WrongViewCast")
    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month =myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)


        val dpd =  DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ 
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->
                //Toast.makeText(this, "chosen year is $selectedYear, the month is $selectedMonth and the day is $selectedDayOfMonth"
                //    , Toast.LENGTH_LONG).show()

                //this is to fix the issue that does not find it. by declaring the val before
                val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
                //this line above

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time/60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time/60000

                val differenceinMinutes = currentDateToMinutes - selectedDateInMinutes

                //this is to fix the issue that does not find it. by declaring the val before
                val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                //this line above

                tvSelectedDateInMinutes.text = differenceinMinutes.toString()
            }
            ,year
            ,month
            ,day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}


