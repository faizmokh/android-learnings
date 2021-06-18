package com.faizmokhtar.fragmentlifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onTag")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    companion object {
        val TAG = "MainActivity"
    }
}
