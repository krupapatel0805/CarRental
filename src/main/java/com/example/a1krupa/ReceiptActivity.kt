package com.example.a1krupa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ReceiptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        // Get receipt details from intent extras
//        val receiptDetails = intent.getParcelableExtra<ReceiptDetails>("receipt_details")

        // Display receipt details
        findViewById<TextView>(R.id.receipt_reservation_id).text = intent.getStringExtra("reservationId")
        findViewById<TextView>(R.id.receipt_car_type).text = intent.getStringExtra("carType")?: "not available"
        findViewById<TextView>(R.id.receipt_number_hours).text = intent.getIntExtra("hours", 0).toString()
//        findViewById<TextView>(R.id.rental_rate_text_view).text = String.format("$%.2f", receiptDetails?.rentalRate)
        findViewById<TextView>(R.id.receipt_insurance).text =
            intent.getBooleanExtra("hasInsurance", false).toString()
        findViewById<TextView>(R.id.receipt_subtotal).text = intent.getIntExtra("subtotal", 0).toString()
        findViewById<TextView>(R.id.receipt_sales_tax).text = intent.getIntExtra("salesTax", 0).toString()
        findViewById<TextView>(R.id.receipt_total).text = intent.getIntExtra("total", 0).toString()
    }
}
