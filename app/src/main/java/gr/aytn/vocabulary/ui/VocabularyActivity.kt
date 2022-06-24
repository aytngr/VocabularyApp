package gr.aytn.vocabulary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gr.aytn.vocabulary.R
import gr.aytn.vocabulary.adapter.VocabularyAdapter
import gr.aytn.vocabulary.model.WordPair

class VocabularyActivity : AppCompatActivity(),View.OnCreateContextMenuListener {

    private lateinit var vocabularyViewModel: VocabularyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)

        var deleteAllButton: Button = findViewById(R.id.delete_all_button)

        vocabularyViewModel = ViewModelProvider(this).get(VocabularyViewModel::class.java)

        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.layoutManager = LinearLayoutManager(this)

//        registerForContextMenu(recyclerview)

        vocabularyViewModel.getWordPairs().observe(this, Observer{
            val adapter = VocabularyAdapter(it as ArrayList<WordPair>,this)
            recyclerview.adapter = adapter


        })

        val addButton: Button = findViewById(R.id.add_word)
        val etWord: EditText = findViewById(R.id.et_word)
        val etTranslation: EditText = findViewById(R.id.et_translation)

        addButton.setOnClickListener{
            if (etWord.text.toString() == ""){
                etWord.setError("Please enter the word.")
            }
            else if (etTranslation.text.toString() == ""){
                etTranslation.setError("Please enter the translation.")
            }else{
                vocabularyViewModel.addWordPair(WordPair(etWord.text.toString(),etTranslation.text.toString()))
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
                Toast.makeText(this,"All deleted",Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            builder.show()

        }
    }

}