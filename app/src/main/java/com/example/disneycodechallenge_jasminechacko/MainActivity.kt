package com.example.disneycodechallenge_jasminechacko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.disneycodechallenge_jasminechacko.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.rvGuestList
import kotlinx.android.synthetic.main.activity_main.rv_noReservationList
import kotlinx.android.synthetic.main.activity_main.tv_haveReservationTitle
import kotlinx.android.synthetic.main.activity_main.tv_info
import kotlinx.android.synthetic.main.activity_main.tv_noReservationTitle
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_guest.*
import kotlinx.android.synthetic.main.item_guest.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var guestsWithReservationsAdapter: GuestAdapter
    private lateinit var guestsWithoutReservationsAdapter: GuestAdapter
    private lateinit var allGuests: MutableList<Guest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        createGuestList()
        var guestsWithReservationsList = createGuestWithReservationList()
        var guestsWithoutReservationsList = createGuestWithoutReservationList()
        guestsWithReservationsAdapter = GuestAdapter(guestsWithReservationsList)
        guestsWithoutReservationsAdapter = GuestAdapter(guestsWithoutReservationsList)

         if (guestsWithReservationsAdapter.itemCount != 0) {
            rvGuestList.adapter = guestsWithReservationsAdapter
            rvGuestList.layoutManager = LinearLayoutManager(this)
        } else {
            tv_haveReservationTitle.visibility = View.GONE
        }

         if (guestsWithoutReservationsAdapter.itemCount != 0) {
            rv_noReservationList.adapter = guestsWithoutReservationsAdapter
            rv_noReservationList.layoutManager = LinearLayoutManager(this)
        } else {
            tv_noReservationTitle.visibility = View.GONE
            tv_info.visibility = View.GONE
        }

        if (savedInstanceState != null) {
            guestsWithReservationsList = savedInstanceState.get(guestsWithReservationsList.toString()) as MutableList<Guest>
            guestsWithoutReservationsList = savedInstanceState.get(guestsWithoutReservationsList.toString()) as MutableList<Guest>
        }

        binding.btnContinue.setOnClickListener {

            var guestWithReservationCount = 0
            var guestWithoutReservationCount = 0


            for (guest in allGuests) {
                if (guest.hasReservation && guest.isChecked) {
                    guestWithReservationCount += 1
                } else if (!guest.hasReservation && guest.isChecked) {
                    guestWithoutReservationCount += 1
                }
            }

            val intent = if (guestWithReservationCount > 0 && guestWithoutReservationCount == 0) {
                Intent(this, ConfirmationScreen::class.java)
            } else if (guestWithReservationCount == 0 && guestWithoutReservationCount >= 0) {
                Intent(this, NoReservationScreen::class.java)
            } else {
                Intent(this, ConflictScreen::class.java)
            }

            startActivity(intent)
        }
    }

    private fun createGuestList() {
        // with reservations
        val guest1 = Guest("Viji Chacko", false, true)
        val guest2 = Guest("Shiny Chacko", false, true)
//        val guest3 = Guest("Jensine Joseph", false, true)
//        val guest4 = Guest("Jason Chacko", false, true)
//        val guest5 = Guest("Lynns Chacko", false, true)
//        val guest6 = Guest("Jasmine Chacko", false, true)
//        val guest15 = Guest("Dwight Schrute", false, true)

        // without reservations
        val guest7 = Guest("Jeremiah Joseph", false, false)
        val guest8 = Guest("Beckham Joseph", false, false)
//        val guest9 = Guest("Lyla Chacko", false, false)
//        val guest10 = Guest("Pattu Chacko", false, false)
//        val guest11 = Guest("Birdie Chacko", false, false)
//        val guest12 = Guest("Kitty Chacko", false, false)
//        val guest13 = Guest("Simba Joseph", false, false)
//        val guest14 = Guest("Michael Scott", false, false)
//        val guest16 = Guest("Jim Halpert", false, false)

        allGuests = mutableListOf(
            // with reservations
            guest1, guest2,
                    // guest3, guest4, guest5, guest6, guest15,

            // without reservations
            guest7, guest8
                    // guest9, guest10, guest11, guest12, guest13, guest14, guest16
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

    fun onCheckBoxClick(view: View) {
        val checkBox = view.findViewById<CheckBox>(R.id.cbCheckGuest)

        if (checkBox.isChecked) {
            for (guest in this.allGuests) {
                if (guest.title == this.tvGuestTitle.toString()) {
                    guest.isChecked = true
                }
            }
        }
        // guestsWithReservationsAdapter.getGuestList()
    }
}