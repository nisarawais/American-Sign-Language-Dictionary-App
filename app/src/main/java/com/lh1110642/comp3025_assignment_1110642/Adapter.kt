package com.lh1110642.comp3025_assignment_1110642

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter(private val wordList: ArrayList<Word>?,private val onViewSignClickListener: OnViewSignClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//           val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_view_sign, parent,false)
           val v = LayoutInflater.from(parent.context).inflate(R.layout.row_word, parent,false)
           return ViewHolder(v)
       }
       override fun getItemCount(): Int{
           return wordList!!.size
       }
       override fun onBindViewHolder(viewHolder: ViewHolder, position: Int){
           val wordz = wordList!![position]

           val id:String? = wordz.id
           val word: String? = wordz.word
           val videoUri: String? = wordz.videoUri
           val description: String? = wordz.description

           viewHolder.itemView.setOnClickListener{
               onViewSignClickListener.onViewSignItemClicked(wordz)
           }
//           viewHolder.wordTextView.text = word
//           setVideoUrl(wordz,viewHolder)
//           viewHolder.descriptionTextView.text = description

           /// for row
           viewHolder.wordText.text = id
           }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val wordTextView = itemView.findViewById<TextView>(R.id.word)
//        val video = itemView.findViewById<VideoView>(R.id.video_signing)
//        val descriptionTextView = itemView.findViewById<TextView>(R.id.description)


        // for row
        val wordText: TextView = itemView.findViewById(R.id.wordline)
    }

    private fun setVideoUrl(wordz: Word, holder: ViewHolder) {
//        val videoUrl:String? = wordz.videoUri
//        val videoUri = Uri.parse(videoUrl)
//
//        val mediaController = MediaController(context)
//        mediaController.setAnchorView(holder.video)
//        holder.video.setVideoURI(videoUri)
//        holder.video.requestFocus()
//
//        holder.video.setOnPreparedListener {mediaPlayer ->
//            mediaPlayer.start()
//        }
    }
   }

