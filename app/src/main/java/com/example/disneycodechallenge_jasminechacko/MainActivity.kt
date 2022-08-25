package com.example.disneycodechallenge_jasminechacko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var guestsWithReservationsAdapter: GuestAdapter
    private lateinit var guestsWithoutReservationsAdapter: GuestAdapter
    private lateinit var allGuests: MutableList<Guest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        createGuestList()
        guestsWithReservationsAdapter = GuestAdapter(createGuestWithReservationList())
        guestsWithoutReservationsAdapter = GuestAdapter(createGuestWithoutReservationList())

        rvGuestList.adapter = guestsWithReservationsAdapter
        rvGuestList.layoutManager = LinearLayoutManager(this)

        rv_noReservationList.adapter = guestsWithoutReservationsAdapter
        rv_noReservationList.layoutManager = LinearLayoutManager(this)

        val buttonClick = findViewById<Button>(R.id.btnContinue)
        buttonClick.setOnClickListener {
            val intent = Intent(this, ConflictScreen::class.java)

            startActivity(intent)
        }
    }

    private fun createGuestList() {
        // with reservations
        val guest1 = Guest("Viji Chacko", false, true)
        val guest2 = Guest("Shiny Chacko", false, true)
        val guest3 = Guest("Jensine Joseph", false, true)
        val guest4 = Guest("Jason Chacko", false, true)
        val guest5 = Guest("Lynns Chacko", false, true)
        val guest6 = Guest("Jasmine Chacko", false, true)
        val guest15 = Guest("Dwight Schrute", false, true)

        // without reservations
        val guest7 = Guest("Jeremiah Joseph", false, false)
        val guest8 = Guest("Beckham Joseph", false, false)
        val guest9 = Guest("Lyla Chacko", false, false)
        val guest10 = Guest("Pattu Chacko", false, false)
        val guest11 = Guest("Poopy Chacko", false, false)
        val guest12 = Guest("Kitty Chacko", false, false)
        val guest13 = Guest("Simba Joseph", false, false)
        val guest14 = Guest("Michael Scott", false, false)

        allGuests = mutableListOf<Guest>(
            guest1, guest2, guest3, guest4, guest5, guest6, guest7, guest8, guest9,
            guest10, guest11, guest12, guest13, guest14, guest15
        )
    }

    private fun createGuestWithReservationList() : MutableList<Guest> {
        val guestsWithReservations = mutableListOf<Guest>()
        for (guest in allGuests) {
            if (guest.hasReservation) {
                guestsWithReservations.add(guest)
            }
        }

        return guestsWithReservations
    }

    private fun createGuestWithoutReservationList() : MutableList<Guest> {
        val guestsWithoutReservations = mutableListOf<Guest>()
        for (guest in allGuests) {
            if (!guest.hasReservation) {
                guestsWithoutReservations.add(guest)
            }
        }

        return guestsWithoutReservations
    }
}