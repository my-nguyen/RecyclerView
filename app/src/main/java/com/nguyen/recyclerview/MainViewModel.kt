package com.nguyen.recyclerview

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "Truong-MainViewModel"

class MainViewModel : ViewModel() {
    private var _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts

    private var _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    init {
        Log.i(TAG, "init")
        _contacts.value = createContacts()
        _isRefreshing.value = false
    }

    fun fetchNewContact() {
        Log.i(TAG, "fetchNewContact")
        _isRefreshing.value = true
        Handler().postDelayed({
            val list = _contacts.value!!.toMutableList()
            list.add(0, Contact("Julius Campbell", 52))
            _contacts.value = list
            _isRefreshing.value = false
        }, 1000)
    }

    private fun createContacts(): List<Contact> {
        Log.i(TAG, "createContacts")
        val contacts = mutableListOf<Contact>()
        for (i in 1..150)
            contacts.add(Contact("Person #$i", i))
        return contacts
    }
}