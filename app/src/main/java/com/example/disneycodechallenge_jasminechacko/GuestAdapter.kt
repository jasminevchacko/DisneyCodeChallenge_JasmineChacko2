package com.example.disneycodechallenge_jasminechacko

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_guest.view.*

class GuestAdapter(
    private val guests: MutableList<Guest>
    ) : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        return GuestViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_guest,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        val curGuest = guests[position]
        holder.itemView.apply {
            tvGuestTitle.text = curGuest.title
            cbCheckGuest.isChecked = curGuest.isChecked
        }
    }

    override fun getItemCount(): Int {
        return guests.size
    }

    fun getGuestList(): MutableList<Guest> {
        return guests
    }

    inner class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkbox = itemView.cbCheckGuest

        init {
            checkbox.setOnClickListener {
                guests[adapterPosition].isChecked = !guests[adapterPosition].isChecked
            }
        }
    }
}

















