package com.lh1110642.comp3025_assignment_1110642
import androidx.fragment.app.Fragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lh1110642.comp3025_assignment_1110642.R
import com.lh1110642.comp3025_assignment_1110642.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.hide()

        //navigate back to the main page
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        //navigate to the home page
        binding.loginButton.setOnClickListener {
            startActivity(Intent(this,InsideMainActivity::class.java))

        }
    }

}