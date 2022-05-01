package com.lh1110642.comp3025_assignment_1110642

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lh1110642.comp3025_assignment_1110642.databinding.FragmentHomeBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
//    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }


    }

    @SuppressLint("IntentReset")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        auth = Firebase.auth
//        binding.backButton.setOnClickListener{
//            Firebase.auth.signOut()
//            val intent = Intent (this.context, MainActivity::class.java)
//            startActivity(intent)
//        }


//        if(auth.currentUser!!.email.toString().contains("nisarawais246@gmail.com"))
//        {
//            binding.addNewSign.setOnClickListener {
//                val intent = Intent (this.context, AddNewSignActivity::class.java)
//                startActivity(intent)
//            }
//        }
//        else{
//            binding.addNewSign.hide()
//        }

//        binding.addNewSign.hide()
//        binding.backButton.visibility = View.GONE

        binding.mail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.data = Uri.parse("mailto:nisarawais246@gmail.com")
            emailIntent.type = "plain/text"
            startActivity(emailIntent)

        }

        binding.ig.setOnClickListener {
            //Initialize link and package
            val sAppLink = "https://www.instagram.com/thedeaffutureaudiologist"
            val sPackage = "com.instagram.android"

            openLink(sAppLink,sPackage,sAppLink)
        }

        binding.tw.setOnClickListener{
            val sAppLink = "twitter://user?screen_name=awaisnisar_"
            val sPackage = "com.twitter.android"
            val sWebLink = "https://www.twitter.com/awaisnisar_"

            openLink(sAppLink,sPackage,sWebLink)
        }
        return binding.root



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }

    private fun openLink(sAppLink: String, sPackage: String, sWebLink: String) {
        //Use try catch
        try {
            // when application is installed
            // Initialize uri
            val uri = Uri.parse(sAppLink)

            //Initialize intent
            val intent = Intent(Intent.ACTION_VIEW)

            //Set data
            intent.data = uri

            //Set package
            intent.setPackage(sPackage)

            //Set flag
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            //Start Activity
            startActivity(intent)
        } catch (activityNotFoundException: ActivityNotFoundException) {
            //open link in browser
            // Initialize uri
            val uri = Uri.parse(sWebLink)

            //Initialize intent
            val intent = Intent(Intent.ACTION_VIEW)

            //Set data
            intent.data = uri

            //Set flag
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            //Start Activity
            startActivity(intent)
        }
    }

}