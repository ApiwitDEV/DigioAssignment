package com.example.digioassignment.ui

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast

class EventHandle() {
    fun itemOnClick() {
        
    }
    fun itemOnLongClick(v: View): Boolean {
        v.visibility = View.GONE
        Log.i("BAS","OK")
        return false
    }
}