package com.lh1110642.comp3025_assignment_1110642

import android.media.MediaPlayer.OnPreparedListener
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lh1110642.comp3025_assignment_1110642.databinding.ActivityViewSignBinding


class ViewSignActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewSignBinding

    private lateinit var word: Word
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewSignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        word = intent.getSerializableExtra(KEY_WORD_DATA) as Word
        getSupportActionBar()?.hide()

        binding.backButton.setOnClickListener{
            finish()
        }

        binding.word.text = word.word
        binding.description.text = word.description

        try {
            binding.videoSigning.setVideoPath(word.videoUri)
            binding.videoSigning.requestFocus()
            binding.videoSigning.start()
            binding.videoSigning.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })

        }catch (e: Exception){
            Log.e("ViewSigningActivity", "onCreate: $e")
        }



    }


}

const val KEY_WORD_DATA = "key_word_data"