package com.myfinancialstory.feature.session

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.myfinancialstory.feature.MainActivity
import com.myfinancialstory.feature.R

class LoginActivity: AppCompatActivity() {

    private lateinit var sessionHandler: SessionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionHandler = SessionHandler(this)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.button_login)
        button.setOnClickListener {
            if (obligatoryFieldsNotNull()) {
                makeSuccessfulLogin()
            } else {
                Toast.makeText(this, R.string.login_required_fields, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun obligatoryFieldsNotNull(): Boolean {

        return findViewById<EditText>(R.id.text_usuario).text.toString() != "" &&
                findViewById<EditText>(R.id.text_contraseña).text.toString() != ""
    }

    private fun makeSuccessfulLogin() {
        sessionHandler.createSession()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
