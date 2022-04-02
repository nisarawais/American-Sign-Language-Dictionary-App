package com.lh1110642.comp3025_assignment_1110642

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lh1110642.comp3025_assignment_1110642.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.hide()

        // Initialize Firebase Auth
        auth = Firebase.auth

        // navigate to the sign up page
        binding.signupButton.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        // navigate to the log in page
        binding.loginButton.setOnClickListener{
            startActivity(Intent(this,SignInActivity::class.java))
        }

    }

    // check to see if the user is already log in
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun reload() {
        startActivity(Intent(this,InsideMainActivity::class.java))
    }
}
