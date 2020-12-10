package com.example.cryptocurrencytracker.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencytracker.R
import com.example.cryptocurrencytracker.databinding.ActivitySignUpBinding
import com.example.cryptocurrencytracker.services.AuthInterface
import com.example.cryptocurrencytracker.utils.startMainActivity
import com.example.cryptocurrencytracker.viewmodel.AuthViewModel
import com.example.cryptocurrencytracker.viewmodel.AuthViewModelProvider
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), AuthInterface, KodeinAware {

    override val kodein by kodein()
    private val provider: AuthViewModelProvider by instance()
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySignUpBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        viewModel = ViewModelProvider(this, provider).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        (viewModel).authListener = this
    }

    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
        Intent(this, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        startMainActivity()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}