package com.lh1110642.comp3025_assignment_1110642

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lh1110642.comp3025_assignment_1110642.databinding.FragmentSearchBinding
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment(), OnViewSignClickListener {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterSigning: Adapter
    var wordList: ArrayList<Word> = ArrayList()
    var filteredWordList: ArrayList<Word> = ArrayList()
    var searchKey = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root


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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

            binding.clear.setOnClickListener{
                binding.searchBar.text.clear()
            }

            binding.searchButton.setOnClickListener {
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(requireActivity().currentFocus!!.windowToken, 0)
            }

    }




    private fun initRecyclerView() {
        wordList = ArrayList()

        val rv = binding.wordListrv
        rv.layoutManager = LinearLayoutManager(activity)
        adapterSigning = Adapter(filteredWordList,this@SearchFragment)
        rv.adapter = adapterSigning

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
                applyFilter()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        )

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(e: Editable?) {
                e?.let {
                    searchKey = it.toString()
                    applyFilter()
                }


            }
        })
    }



    override fun onViewSignItemClicked(word: Word) {
        val intent  = Intent(context, ViewSignActivity::class.java)
        intent.putExtra(KEY_WORD_DATA, word)
        startActivity(intent)

    }


    private fun applyFilter(){
        filteredWordList.clear()
        filteredWordList.addAll(wordList.filter { it.word?.contains(searchKey, true)?:false })
        adapterSigning.notifyDataSetChanged()
    }

}
