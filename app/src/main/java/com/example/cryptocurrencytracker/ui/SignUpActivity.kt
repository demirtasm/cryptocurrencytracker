package com.example.cryptocurrencytracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cryptocurrencytracker.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUp()
    }
    fun signUp(){
        btn_sign_up.setOnClickListener {
            if(edt_mail.text.isNotEmpty() && edt_password.text.isNotEmpty() && edt_password_repeat.text.isNotEmpty()){
                if(edt_password.text.toString().equals(edt_password_repeat.text.toString())){
                    signUpCreate(edt_mail.text.toString(), edt_password.text.toString())
                }
            }
        }
    }
    fun signUpCreate(mail: String, pass:String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, pass)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                override fun onComplete(p0: Task<AuthResult>) {
                    if(p0.isSuccessful){
                        Toast.makeText(
                            this@SignUpActivity,
                            "Üye olundu",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
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