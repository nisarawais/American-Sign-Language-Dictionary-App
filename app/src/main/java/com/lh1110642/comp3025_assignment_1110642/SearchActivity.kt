package com.lh1110642.comp3025_assignment_1110642

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lh1110642.comp3025_assignment_1110642.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSupportActionBar()?.hide()


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
    }
}