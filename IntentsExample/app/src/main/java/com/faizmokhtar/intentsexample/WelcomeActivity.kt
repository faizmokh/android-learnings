package com.faizmokhtar.intentsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        intent?.let {
            val fullName = it.getStringExtra(FULL_NAME_KEY)
            findViewById<TextView>(R.id.welcome_text).text = getString(R.string.welcome_text, fullName)
        }
    }
}
