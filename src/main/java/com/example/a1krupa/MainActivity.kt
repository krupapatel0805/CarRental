package com.example.a1krupa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import kotlin.random.Random
import kotlin.math.ceil
import android.content.Intent


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var carTypeRadioGroup: RadioGroup
        val hoursEditText: EditText = findViewById(R.id.hours_edit_text)
        lateinit var insuranceSwitch: Switch
        val errorTextView: TextView = findViewById(R.id.error_message_text_view)


        // Initializing UI components from xml
        val carType: RadioGroup = findViewById(R.id.car_type_radio_group)
//        val suv: RadioButton = findViewById(R.id.suv_radio_button)
//        val sedan: RadioButton = findViewById(R.id.sedan_radio_button)
        val carHours: EditText = findViewById(R.id.hours_edit_text)
        val insurance: Switch = findViewById(R.id.car_insurance_switch)
//        val error: TextView = findViewById(R.id.error_message_text_view)

        // Adding click listener to the Start Over button
        findViewById<Button>(R.id.start_over_button).setOnClickListener {
            fun resetForm() {

                // Clear radio group selection
                carTypeRadioGroup.clearCheck()

                // Clear hours edit text
                hoursEditText.text.clear()

                // Set insurance switch to off
                insuranceSwitch.isChecked = false

                // Clear error message
                errorTextView.text = ""
            }
        }

        //Adding click listener to the Reserve button
        findViewById<Button>(R.id.reserve_button).setOnClickListener {
            fun validateForm() {
                // Get selected car type
                val carType = when (carTypeRadioGroup.checkedRadioButtonId) {
                    R.id.suv_radio_button -> "SUV"
                    R.id.sedan_radio_button -> "Sedan"
                    else -> null
                }
            }
        }

        // Getting number of hours
        val hours = hoursEditText.text.toString().trim()

        // Validate car type and hours
        if (carType == null) {
            errorTextView.text = "Please select a car type."
        } else if (hours.isEmpty()) {
            errorTextView.text = "Please enter the number of hours."
        } else if (hours.toInt() < 1) {
            errorTextView.text = "Number of hours must be at least 1."
        } else {
            // If the Form is valid, generate receipt details and navigate to receipt screen
            val reservationId = "${carType.toString().substring(0, 3)}-${Random.nextInt(100, 1000)}"

            val carType: String = ""
            val rentalRate = if (hours.toInt() <= 4) {
                if (carType == "Sedan") 5.0 else 7.0
            } else {
                50.0 * ceil(hours.toInt() / 24.0)
            }

            val insuranceFee = if (insuranceSwitch.isChecked) {
                0.5 * hours.toInt()
            } else {
                0.0
            }

            val subtotal = rentalRate + insuranceFee

            val salesTax = 0.13 * subtotal

            val total = subtotal + salesTax

//
            fun receiptDetails(
                reservationId: String,
                carType: String,
                hours: Int,
                hasInsurance: Boolean,
                subtotal: Double,
                salesTax: Double,
                total: Double
            ): String {
                return "Reservation ID: $reservationId\n" +
                        "Car Type: $carType\n" +
                        "Hours: $hours\n" +
                        "Insurance: ${if (hasInsurance) "Yes" else "No"}\n" +
                        "Rental Rate: $$subtotal\n" +
                        "Sales Tax: $$salesTax\n" +
                        "Total: $$total"
            }

            val reserve = findViewById<Button>(R.id.reserve_button)
            reserve.setOnClickListener {
                val intent = Intent(this, ReceiptActivity::class.java).apply {
                    putExtra("reservationId",reservationId)
                    putExtra("carType", carType)
                    putExtra("rentalHours", hours)
                    putExtra("hasInsurance", insurance.isChecked)
                    putExtra("subtotal", subtotal)
                    putExtra("salesTax", salesTax)
                    putExtra("total", total)
                }
                startActivity(intent)
            }

        }
    }
}
