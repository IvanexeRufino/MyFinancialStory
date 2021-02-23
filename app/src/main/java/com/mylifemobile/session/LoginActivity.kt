package com.mylifemobile.session

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mylifemobile.MainActivity
import com.mylifemobile.R
import com.mylifemobile.api.RestClientManager
import com.mylifemobile.api.model.ExpensesModel
import com.mylifemobile.api.model.UserModel
import org.jetbrains.anko.doAsync
import java.util.*

class LoginActivity: AppCompatActivity() {

    private lateinit var sessionHandler: SessionHandler
    private val restClient: RestClientManager = RestClientManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionHandler = SessionHandler(this)
        setContentView(R.layout.activity_login)

        createLoginButton()
        createRegisterButton()
    }

    private fun createLoginButton() {
        val button = findViewById<Button>(R.id.button_login)
        button.setOnClickListener {
            val email = findViewById<EditText>(R.id.text_usuario).text.toString()
            val password = findViewById<EditText>(R.id.text_contraseña).text.toString()

            if (email != "" && password != "") {
                goToLogin()
            } else {
                Toast.makeText(this, R.string.login_required_fields, Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun createRegisterButton() {
        val button = findViewById<Button>(R.id.button_register)
        button.setOnClickListener {
            val email = findViewById<EditText>(R.id.text_usuario).text.toString()
            val password = findViewById<EditText>(R.id.text_contraseña).text.toString()

            if (email != "" && password != "") {
                val user = UserModel(
                    email = email,
                    password = password
                )
                makeSuccessfulLogin(user)
            } else {
                Toast.makeText(this, R.string.login_required_fields, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun makeSuccessfulLogin(user: UserModel) {
        doAsync {
            val userCreated = restClient.createUser(user)
            if(userCreated.id != 0) {
                sessionHandler.createSession(userCreated.id)
                goToLogin()
            }
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
