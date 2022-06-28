package gr.aytn.vocabulary.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import gr.aytn.roomexample.WordPairDatabase
import gr.aytn.vocabulary.R
import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addVocabButton: Button = findViewById(R.id.add_vocab)
        val checkVocabButton: Button = findViewById(R.id.check_vocab)



        addVocabButton.setOnClickListener {
            val intent = Intent(this, VocabularyActivity::class.java)
            startActivity(intent)
        }
        checkVocabButton.setOnClickListener {
            mainViewModel.count.observe(this, Observer {
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