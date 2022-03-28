package com.lh1110642.comp3025_assignment_1110642

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lh1110642.comp3025_assignment_1110642.R

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        getSupportActionBar()?.hide()
    }
}