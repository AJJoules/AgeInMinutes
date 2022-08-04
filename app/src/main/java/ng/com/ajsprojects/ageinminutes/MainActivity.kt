package ng.com.ajsprojects.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener{view ->
            clickDatePicker(view)
            Toast.makeText(this, "Button Works", Toast.LENGTH_LONG).show()
        }
    }

    private fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                _, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this,"The chosen date is $selectedDayOfMonth/$selectedMonth/$selectedYear", Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            val tvSelected = findViewById<TextView>(R.id.tvSelectedDate)
            tvSelected.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time/60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateToMinutes = currentDate!!.time/60000

            val differenceInMinutes =currentDateToMinutes - selectedDateInMinutes

            val tvDateInMinutes = findViewById<TextView>(R.id.tvDateInMinutes)

            tvDateInMinutes.text = differenceInMinutes.toString()
        }
            , year
            , month
            , day)

        dpd.datePicker.maxDate = Date().time - 8640000
        dpd.show()
    }
}