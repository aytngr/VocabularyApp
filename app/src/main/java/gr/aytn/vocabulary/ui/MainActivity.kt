package gr.aytn.vocabulary.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import gr.aytn.roomexample.WordPairDatabase
import gr.aytn.vocabulary.R
import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addVocabButton: Button = findViewById(R.id.add_vocab)
        val checkVocabButton: Button = findViewById(R.id.check_vocab)

        val db = WordPairDatabase.getDatabase(application)
        val wordPairDao = db.wordPairDao()
        val repository = WordPairRepository(wordPairDao)
        val count = repository.count

        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        addVocabButton.setOnClickListener {
            val intent = Intent(this, VocabularyActivity::class.java)
            startActivity(intent)
        }
        checkVocabButton.setOnClickListener {
            count.observe(this, Observer {
                if(it != 0){
                    val intent = Intent(this, QuizActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Your vocabulary is empty.",Toast.LENGTH_SHORT).show()
                    finish()
                    startActivity(getIntent())
                }
            })



        }
    }
}