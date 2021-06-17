package com.faizmokhtar.launchlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        intent.let {
            val fullName = it.getStringExtra(USERNAME_KEY)
            findViewById<TextView>(R.id.page_title).text = "Hello $fullName, welcome! Hope you enjoy using our app."
        }
    }
}
