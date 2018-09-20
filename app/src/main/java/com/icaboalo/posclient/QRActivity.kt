package com.icaboalo.posclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_qr.*


class QRActivity : AppCompatActivity() {

    var qrActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)

        i_qr.setOnLongClickListener {
            if (qrActive)
                i_qr.setImageResource(R.drawable.qr_1)
            else
                i_qr.setImageResource(R.drawable.qr_2)
            qrActive = !qrActive
            updateFirebase()
            return@setOnLongClickListener true
        }
        updateFirebase()
    }

    fun updateFirebase() {
        val database = FirebaseDatabase.getInstance()
        var reference =
        if (qrActive) {
            database.getReference("VWr88B3cqFCfjXVSYCCc")
        } else {
            database.getReference("xjewBnApnJM6sKgUvHVA")
        }
        reference.setValue(null)
        reference.child("server").setValue(qrActive)
        listenFirebase()
    }

    fun listenFirebase() {
        val database = FirebaseDatabase.getInstance()
        var reference =
                if (qrActive) {
                    database.getReference("VWr88B3cqFCfjXVSYCCc")
                } else {
                    database.getReference("xjewBnApnJM6sKgUvHVA")
                }
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    val intent = Intent(this@QRActivity, ResultActivity::class.java)
                    intent.putExtra("success",  snapshot.child("client").getValue(Boolean::class.java)!!)
                    intent.putExtra("amount",  snapshot.child("amount").getValue(String::class.java))
                    reference.removeEventListener(this)
                    startActivity(intent)
                }
            }
        })
    }
}
