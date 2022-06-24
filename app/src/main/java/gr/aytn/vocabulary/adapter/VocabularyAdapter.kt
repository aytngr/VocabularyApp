package gr.aytn.vocabulary.adapter

import android.content.Context
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

class VocabularyAdapter(private val mList: ArrayList<WordPair>, private val context: Context): RecyclerView.Adapter<VocabularyAdapter.ViewHolder>() {
    private lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    lateinit var repository: WordPairRepository


        view = LayoutInflater.from(parent.context).inflate(R.layout.word, parent, false)
//        view.setOnCreateContextMenuListener{
//                menu: ContextMenu, v: View,
//                menuInfo: ContextMenu.ContextMenuInfo? ->
//            menu.add("Delete").setOnMenuItemClickListener{
//                Log.i("efwf","Deleted")
//                return@setOnMenuItemClickListener true
//            }
//        }
        return ViewHolder(view)
    }


    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wordPairDao = WordPairDatabase.getDatabase(context).wordPairDao()
        val repository = WordPairRepository(wordPairDao)

        val wordPair = mList[position]
        holder.tvWord.text = wordPair.word
        holder.tvTranslation.text = wordPair.translation

        holder.cardView.setOnCreateContextMenuListener{
                menu: ContextMenu, v: View,
                menuInfo: ContextMenu.ContextMenuInfo? ->
            menu.add("Delete").setOnMenuItemClickListener{
                CoroutineScope(Dispatchers.IO).launch {
                    repository.deleteWordPair(wordPair)
                }
                notifyItemRemoved(position)
                notifyDataSetChanged()
                return@setOnMenuItemClickListener true
            }
        }



    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){
        var tvWord: TextView = itemView.findViewById(R.id.word)
        val tvTranslation: TextView = itemView.findViewById(R.id.translation)
        var cardView: CardView = itemView.findViewById(R.id.card_view)


//        override fun onCreateContextMenu(
//            menu: ContextMenu, v: View,
//            menuInfo: ContextMenu.ContextMenuInfo?
//        ) {
//            menu.add(this.adapterPosition, 1, 0, "Delete")
//
//            override fun onContextItemSelected(item: MenuItem): Boolean {
//                val id = item.itemId
//                when (id) {
//                    R.id.delete -> {
//                        Toast.makeText(VocabularyActivity(), "Deleted", Toast.LENGTH_SHORT)
//                        return true
//                    }
//                    else -> return false
//                }
//            }
//        }





    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
