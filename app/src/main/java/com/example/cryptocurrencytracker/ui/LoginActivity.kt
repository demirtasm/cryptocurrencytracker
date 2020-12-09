package com.example.cryptocurrencytracker.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrencytracker.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login()
    }

    fun login() {
        btn_login.setOnClickListener {
            if (edt_mail.text.isNotEmpty() && edt_password.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    edt_mail.text.toString(),
                    edt_password.text.toString()
                )
                    .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                        override fun onComplete(p0: Task<AuthResult>) {
                            if (p0.isSuccessful) {
                                initAuthStateListener()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Something wrong: ${p0.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            }
        }
    }
    fun initAuthStateListener(){
        var intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}