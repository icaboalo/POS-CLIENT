package com.icaboalo.posclient

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pay.*

class PayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        i_qr.setOnClickListener {
            startActivity(Intent(this, QRActivity::class.java))
        }
    }
}
