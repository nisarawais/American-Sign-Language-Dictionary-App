package com.lh1110642.comp3025_assignment_1110642

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lh1110642.comp3025_assignment_1110642.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        openFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    openFragment(HomeFragment())
                    true
                }
                R.id.search -> {
                    openFragment(SearchFragment())
                    true
                }
                R.id.favorite -> {
                    openFragment(FavoriteFragment())
                    true
                }
                R.id.contact_us -> {
                    openFragment(ContactUsFragment())
                    true
                }
                else -> {
                   false
                }
            }
        }
    }

    fun openFragment(fragment: Fragment){

        supportFragmentManager.beginTransaction()
//            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .addToBackStack(null)
            .replace(R.id.frame_layout, fragment).commit()
    }


}
