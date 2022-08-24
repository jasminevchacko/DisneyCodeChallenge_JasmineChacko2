package com.example.disneycodechallenge_jasminechacko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var guestAdapter: GuestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        guestAdapter = GuestAdapter(mutableListOf())

        rvGuestList.adapter = guestAdapter
        rvGuestList.layoutManager = LinearLayoutManager(this)

        btnAddGuest.setOnClickListener {
            val guestTitle = etGuestTitle.text.toString()
            if(guestTitle.isNotEmpty()) {
                val guest = Guest(guestTitle)
                guestAdapter.addGuest(guest)
                etGuestTitle.text.clear()
            }
        }

        val buttonClick = findViewById<Button>(R.id.btnContinue)
        buttonClick.setOnClickListener {
            val intent = Intent(this, ConflictScreen::class.java)

            startActivity(intent)
        }
    }
}