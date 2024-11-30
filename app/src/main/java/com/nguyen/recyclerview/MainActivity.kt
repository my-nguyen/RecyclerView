package com.nguyen.recyclerview

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.nguyen.recyclerview.databinding.ActivityMainBinding

private const val TAG = "Truong-MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.i(TAG, "onCreate")
        val contacts = mutableListOf<Contact>()
        val adapter = ContactsAdapter(contacts)
        binding.contacts.adapter = adapter
        binding.contacts.layoutManager = LinearLayoutManager(this)

        val viewModel: MainViewModel by viewModels()
        viewModel.contacts.observe(this) {
            Log.i(TAG, "Received contacts from ViewModel")
            contacts.clear()
            contacts.addAll(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.isRefreshing.observe(this) {
            binding.swipe.isRefreshing = it
        }

        binding.swipe.setOnRefreshListener {
            viewModel.fetchNewContact()
        }
    }
}