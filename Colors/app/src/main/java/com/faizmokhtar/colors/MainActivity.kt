package com.faizmokhtar.colors

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.enter_button).setOnClickListener {
            val redChannel = findViewById<TextInputEditText>(R.id.red_chanel).text.toString().trim()
            val blueChannel = findViewById<TextInputEditText>(R.id.blue_channel).text.toString().trim()
            val greenChannel = findViewById<TextInputEditText>(R.id.green_channel).text.toString().trim()
            val cardView = findViewById<MaterialCardView>(R.id.color_card)
            val cardTitle = findViewById<MaterialTextView>(R.id.color_card_title)

            if (redChannel.isNotEmpty() && blueChannel.isNotEmpty() && greenChannel.isNotEmpty()) {
                val hexToDisplay = "#".plus(redChannel).plus(blueChannel).plus(greenChannel)
                cardTitle?.text = hexToDisplay
                cardView.setCardBackgroundColor(Color.parseColor(hexToDisplay))
            } else {
                Toast.makeText(this, getString(R.string.please_enter_fields), Toast.LENGTH_LONG)
                        .apply {
                            setGravity(Gravity.CENTER, 0, 0)
                            show()
                        }
            }
        }
    }
}
