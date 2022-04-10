package com.lh1110642.comp3025_assignment_1110642

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.*
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.lh1110642.comp3025_assignment_1110642.databinding.ActivityAddNewSignBinding
import java.util.*
import java.util.jar.Manifest
import kotlin.collections.HashMap

class AddNewSignActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewSignBinding
    private lateinit var progressDialog: ProgressDialog
    private var word:String = ""
    private var description:String = ""
    private var urivideo:Uri? = null
    private val galleryActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val videoUri = data!!.data
            urivideo = videoUri
            val mediaController = MediaController(this)
            mediaController.setAnchorView(binding.videoSigning)
            binding.videoSigning.setMediaController(mediaController)
            binding.videoSigning.setVideoURI(videoUri)
        }
        else{
            Toast.makeText(this,"Cancelled", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewSignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.hide()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Uploading Video...")
        progressDialog.setCanceledOnTouchOutside(false)


//
//        navigate back to the home page

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, InsideMainActivity::class.java))
        }
//        binding.backButton.setOnClickListener {
//            startActivity(Intent(this, InsideMainActivity::class.java))
//        }

        //upload new sign to the app
        binding.doneButton.setOnClickListener {
            word = binding.newWord.text.toString().trim().replaceFirstChar { it.uppercase() }
            description = binding.descriptionInput.text.toString()
            if(TextUtils.isEmpty(word))
            {
                Toast.makeText(this,"Word is required",Toast.LENGTH_SHORT).show()
            }
            else if (urivideo == null){
                Toast.makeText(this,"Pick a Video",Toast.LENGTH_SHORT).show()
            }
            else {
                uploadSignFirebase()
            }
        }
//
//        //choose video or make video
//
       binding.floatingActionButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
           intent.type = "video/*"
           galleryActivityResultLauncher.launch(intent)
        }


    }

    private fun uploadSignFirebase() {
        progressDialog.show()
        val filePathAndName= "Videos/video_$word"

        val storageReference = FirebaseStorage.getInstance().getReference(filePathAndName)
        storageReference.putFile(urivideo!!)
            .addOnSuccessListener { taskSnapshot ->
                val uriTask = taskSnapshot.storage.downloadUrl
                while (!uriTask.isSuccessful);
                val downloadUri = uriTask.result
                if(uriTask.isSuccessful) {
                    val hashMap = HashMap<String, Any>()
                    hashMap["id"] = word
                    hashMap["word"] = word
                    hashMap["videoUri"] = "$downloadUri"
                    hashMap["description"] = description

                    val dbReference = FirebaseDatabase.getInstance().getReference("Videos")
                    dbReference.child(word)
                        .setValue(hashMap)
                        .addOnSuccessListener {
                            progressDialog.dismiss()
                            Toast.makeText(this,"New Sign Is Uploaded", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,HomeFragment::class.java))

                        }
                        .addOnFailureListener { e->
                            progressDialog.dismiss()
                            Toast.makeText(this,"${e.message}", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this,"${e.message}", Toast.LENGTH_SHORT).show()
            }

    }

}