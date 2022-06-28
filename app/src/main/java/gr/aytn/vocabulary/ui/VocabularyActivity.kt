package gr.aytn.vocabulary.ui

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import gr.aytn.vocabulary.R
import gr.aytn.vocabulary.adapter.VocabularyAdapter
import gr.aytn.vocabulary.model.WordPair

@AndroidEntryPoint
class VocabularyActivity : AppCompatActivity(), View.OnCreateContextMenuListener{

    private val vocabularyViewModel: VocabularyViewModel by viewModels()
    private lateinit var recyclerview: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)


        var deleteAllButton: Button = findViewById(R.id.delete_all_button)

        recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.layoutManager = LinearLayoutManager(this)

        registerForContextMenu(recyclerview)


//        registerForContextMenu(recyclerview)

        vocabularyViewModel.getWordPairs().observe(this, Observer {
            val adapter = VocabularyAdapter(it as ArrayList<WordPair>)
            recyclerview.adapter = adapter


        })

        val addButton: Button = findViewById(R.id.add_word)
        val etWord: EditText = findViewById(R.id.et_word)
        val etTranslation: EditText = findViewById(R.id.et_translation)

        addButton.setOnClickListener {
            if (etWord.text.toString() == "") {
                etWord.setError("Please enter the word.")
            } else if (etTranslation.text.toString() == "") {
                etTranslation.setError("Please enter the translation.")
            } else {
                vocabularyViewModel.addWordPair(
                    WordPair(
                        etWord.text.toString(),
                        etTranslation.text.toString()
                    )
                )
                recyclerview.adapter?.notifyDataSetChanged()
                etWord.setText("")
                etTranslation.setText("")
            }
        }
        deleteAllButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete All")
            builder.setMessage("Are you sure to delete all words?")
            //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
            builder.setPositiveButton("Delete") { dialog, which ->
                vocabularyViewModel.deleteAll()
                Toast.makeText(this, "All deleted", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            builder.show()

        }

    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu?.add(
            Menu.NONE, R.id.delete,
            Menu.NONE, "Delete")

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val wordpair: WordPair?
        try {
            wordpair = (recyclerview.adapter as VocabularyAdapter).getWordPair()
        } catch (e: Exception) {
            return super.onContextItemSelected(item)
        }
        when (item.itemId) {
            R.id.delete -> {
                if (wordpair != null) {
                    vocabularyViewModel.deleteWordPair(wordpair)
                }
                recyclerview.adapter?.notifyDataSetChanged()
            }
        }
        return super.onContextItemSelected(item)
    }

}