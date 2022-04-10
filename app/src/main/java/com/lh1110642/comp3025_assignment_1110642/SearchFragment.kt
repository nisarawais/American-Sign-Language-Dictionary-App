package com.lh1110642.comp3025_assignment_1110642

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lh1110642.comp3025_assignment_1110642.databinding.FragmentSearchBinding
import java.lang.AssertionError
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterSigning: Adapter
    var wordList: ArrayList<Word> = ArrayList()


    private var param1: String? = null
    private var param2: String? = null

    private val db = FirebaseDatabase.getInstance()
    private val root = db.reference.child("Videos")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initRecyclerView(view)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initRecyclerView(view: View) {
        wordList = ArrayList()
        val rv = view.findViewById<RecyclerView>(R.id.word_listrv)
        rv.layoutManager = LinearLayoutManager(activity)
        val ref = FirebaseDatabase.getInstance().getReference("Videos")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                wordList.clear()
                for (ds in snapshot.children) {
                    val modelWord = ds.getValue(Word::class.java)
                    wordList.add((modelWord!!))
                }
                wordList.sortBy {
                    it.word
                }
                adapterSigning = Adapter(wordList)
                rv.adapter = adapterSigning
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        )
    }

}
