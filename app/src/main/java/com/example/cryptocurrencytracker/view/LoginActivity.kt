package com.example.cryptocurrencytracker.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencytracker.R
import com.example.cryptocurrencytracker.databinding.ActivityLoginBinding
import com.example.cryptocurrencytracker.services.AuthInterface
import androidx.navigation.fragment.NavHostFragment
import com.example.cryptocurrencytracker.utils.startMainActivity
import com.example.cryptocurrencytracker.viewmodel.AuthViewModel
import com.example.cryptocurrencytracker.viewmodel.AuthViewModelProvider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity(), AuthInterface, KodeinAware {

    override val kodein by kodein()
    private val provider: AuthViewModelProvider by instance()
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, provider).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

    }

    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        startMainActivity()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        viewModel.user?.let {
            startMainActivity()
        }
    }
}