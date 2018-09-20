package com.icaboalo.posclient

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        Handler().postDelayed({ checkStatus() }, 5000)

    }

    fun checkStatus() {
        progress.visibility = View.GONE
        result.visibility = View.VISIBLE
        if (intent.getBooleanExtra("success", false)) {
            image_view.setImageResource(R.drawable.checked)
            tv_status.text = "Pago Aprobado"
            tv_amount.text = "Monto: ${intent.getStringExtra("amount")}"
        } else {
            tv_status.text = "Pago Declinado"
            tv_amount.text = ""
            tv_authorization.text = "Fondos insuficientes"
            image_view.setImageResource(R.drawable.error)
        }
    }
}
