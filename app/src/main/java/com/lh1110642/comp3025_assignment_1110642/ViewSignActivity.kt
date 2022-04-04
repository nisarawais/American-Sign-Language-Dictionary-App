package com.lh1110642.comp3025_assignment_1110642

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lh1110642.comp3025_assignment_1110642.databinding.ActivityViewSignBinding

class ViewSignActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewSignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewSignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.hide()

    }
}