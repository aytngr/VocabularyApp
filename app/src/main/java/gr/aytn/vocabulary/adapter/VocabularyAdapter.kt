package gr.aytn.vocabulary.adapter

import android.content.Context
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.viewModelScope

import androidx.recyclerview.widget.RecyclerView
import gr.aytn.roomexample.WordPairDatabase
import gr.aytn.vocabulary.R
import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VocabularyAdapter(private val mList: ArrayList<WordPair>): RecyclerView.Adapter<VocabularyAdapter.ViewHolder>() {
    private lateinit var view: View
//    private var position: Int = 0
    private var item: WordPair? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        view = LayoutInflater.from(parent.context).inflate(R.layout.word, parent, false)

        return ViewHolder(view)
    }


    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wordPair = mList[position]
        holder.tvWord.text = wordPair.word
        holder.tvTranslation.text = wordPair.translation
        holder.itemView.isLongClickable = true

        holder.itemView.setOnLongClickListener{
//            setPosition(holder.adapterPosition)
            setWordPair(wordPair)
            return@setOnLongClickListener false
        }
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){
        var tvWord: TextView = itemView.findViewById(R.id.word)
        val tvTranslation: TextView = itemView.findViewById(R.id.translation)

    }

    override fun getItemCount(): Int {
        return mList.size
    }
//    fun getPosition(): Int{
//        return position
//    }
//    fun setPosition(newPosition: Int){
//        position = newPosition
//    }
    fun getWordPair(): WordPair?{
        return item
    }
    fun setWordPair(wordpair: WordPair){
        item = wordpair
    }
}
