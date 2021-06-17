package com.faizmokhtar.launchlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent

const val USERNAME_KEY = "USERNAME_KEY"
const val PASSWORD_KEY = "PASSWORD_KEY"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.confirm_button).setOnClickListener {
            val username = findViewById<EditText>(R.id.username_input).text.toString().trim()
            val password = findViewById<EditText>(R.id.password_input).text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                if (username.toLowerCase().equals(USERNAME) &&
                    password.equals(PASSWORD)) {
                    Intent(this, WelcomeActivity::class.java).also { welcomeIntent ->
                        welcomeIntent.putExtra(USERNAME_KEY, username)
                        startActivity(welcomeIntent)
                    }
                } else {
                    Toast.makeText(this, getString(R.string.invalid_input), Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, getString(R.string.empty_input), Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        const val USERNAME = "@faizmokh"
        const val PASSWORD = "password"
    }
}
