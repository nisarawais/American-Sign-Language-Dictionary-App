package com.lh1110642.comp3025_assignment_1110642

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lh1110642.comp3025_assignment_1110642.databinding.ActivityAddNewSignBinding

class AddNewSignActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewSignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewSignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.hide()

        //navigate back to the home page
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, InsideMainActivity::class.java))
        }
    }
}