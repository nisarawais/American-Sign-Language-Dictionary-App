package com.lh1110642.comp3025_assignment_1110642

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lh1110642.comp3025_assignment_1110642.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.hide()

        binding.bottomNavigationView.visibility = View.GONE

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this,HomeActivity::class.java))
                    true
                }
                R.id.search -> {
                    startActivity(Intent(this,SearchActivity::class.java))
                    true
                }
                R.id.favorite -> {
                    startActivity(Intent(this,FavoriteActivity::class.java))
                    true
                }
                R.id.contact_us -> {
                    startActivity(Intent(this,ContactUsActivity::class.java))
                    true
                }
                else -> {
                   false
                }
            }
        }

        // navigate to the sign up page
        binding.signupButton.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        // navigate to the log in page
        binding.loginButton.setOnClickListener{
            startActivity(Intent(this,SignInActivity::class.java))
        }
    }
}
