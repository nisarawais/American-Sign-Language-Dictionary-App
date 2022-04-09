package com.lh1110642.comp3025_assignment_1110642
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lh1110642.comp3025_assignment_1110642.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.hide()

        //navigate back to the main page
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        auth = Firebase.auth

        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (email.isEmpty() || password.isEmpty())
            {
                Toast.makeText(baseContext, "Please type your log in info",
                    Toast.LENGTH_SHORT).show()
            }
            else{
                signIn(email, password)
            }
        }
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user!= null){
            startActivity(Intent(this,InsideMainActivity::class.java))
        }
        else{
            Toast.makeText(baseContext, "Ooops! Your email or password isn't matching. Please try again.",
                Toast.LENGTH_SHORT).show()
            binding.email.text.clear()
            binding.password.text.clear()
        }

    }

    companion object {
        private const val TAG = "EmailPassword"
    }


}