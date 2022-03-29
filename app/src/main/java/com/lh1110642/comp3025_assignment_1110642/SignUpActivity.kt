package com.lh1110642.comp3025_assignment_1110642

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lh1110642.comp3025_assignment_1110642.R
import com.lh1110642.comp3025_assignment_1110642.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.hide()


        //navigate back to the main page
        binding.backButton.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

        //navigate to the home page
        binding.doneButton.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }
}