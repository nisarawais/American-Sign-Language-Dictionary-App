package com.lh1110642.comp3025_assignment_1110642
//
//import android.content.ContentValues.TAG
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import com.google.firebase.analytics.FirebaseAnalytics
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.ktx.Firebase
//import com.lh1110642.comp3025_assignment_1110642.R
//import com.lh1110642.comp3025_assignment_1110642.databinding.ActivitySignUpBinding
//
//class SignUpActivity : AppCompatActivity() {
//    private lateinit var binding: ActivitySignUpBinding
//    private lateinit var auth: FirebaseAuth
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySignUpBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        getSupportActionBar()?.hide()
//
//
//        //navigate back to the main page
//        binding.backButton.setOnClickListener{
//            startActivity(Intent(this,MainActivity::class.java))
//        }
//
//
//        // Initialize Firebase Auth
//        auth = Firebase.auth
//
//        binding.doneButton.setOnClickListener {
//            val email = binding.email.text.toString()
//            val password = binding.password.text.toString()
//
//            if (email.isEmpty() || password.isEmpty())
//            {
//                Toast.makeText(baseContext, "Please type your sign up info",
//                    Toast.LENGTH_SHORT).show()
//            }
//            else{
//                createAccount(email, password)
//            }
//
//        }
//    }
//
//
//    private fun createAccount(email: String, password: String) {
//        // [START create_user_with_email]
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(baseContext, "Your email and password need to be in the proper format.",
//                        Toast.LENGTH_SHORT).show()
//                    binding.email.text.clear()
//                    binding.password.text.clear()
//                }
//            }
//        // [END create_user_with_email]
//    }
//
//    private fun updateUI(user: FirebaseUser?) {
//        if (user!= null){
//            startActivity(Intent(this,InsideMainActivity::class.java))
//        }
//        else{
//            Toast.makeText(baseContext, "Your email and password need to be in the proper format.",
//                Toast.LENGTH_SHORT).show()
//            binding.email.text.clear()
//            binding.password.text.clear()
//        }
//    }
//
//    companion object {
//        private const val TAG = "EmailPassword"
//    }
//}