package com.lh1110642.comp3025_assignment_1110642
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lh1110642.comp3025_assignment_1110642.databinding.ActivityViewSignBinding

class ViewSignActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewSignBinding
    private lateinit var wordList: ArrayList<Word>
    private lateinit var adapterSigning: Adapter

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
            binding.videoSigning.start()

        }catch (e: Exception){
            Log.e("ViewSigningActivity", "onCreate: $e")
        }


//        loadSignFromFirebase()
    }

//    private fun loadSignFromFirebase() {
//        wordList = ArrayList()
//
//        val ref = FirebaseDatabase.getInstance().getReference("Videos")
//        ref.addValueEventListener(object: ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot){
//                wordList.clear()
//                for(ds in snapshot.children){
//                    val modelWord = ds.getValue(Word::class.java)
//                    wordList.add((modelWord!!))
//                }
//                adapterSigning = Adapter(this@ViewSignActivity,wordList)
//                word_list.adapter = adapterSigning
//            }
//            override fun onCancelled(error:DatabaseError) {
//
//            }
//        }
//
//        )
//    }
}

const val KEY_WORD_DATA = "key_word_data"