package com.nguyen.recyclerview

import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nguyen.recyclerview.databinding.ItemContactBinding

private const val TAG = "ContactsAdapter"

class ContactsAdapter(val contacts: List<Contact>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.name.text = contact.name
            binding.age.text = "Age: ${contact.age}"
            val imageUrl =
                if (itemView.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                    contact.imageUrl
                else
                    contact.imageUrlLandscape
            Glide.with(itemView).load(imageUrl).into(binding.profile)
        }
    }

    // expensive
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = contacts.size

    // NOT expensive
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder at $position")
        holder.bind(contacts[position])
    }
}