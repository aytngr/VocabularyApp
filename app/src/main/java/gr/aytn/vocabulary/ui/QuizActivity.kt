package gr.aytn.vocabulary.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import gr.aytn.roomexample.WordPairDatabase
import gr.aytn.vocabulary.R
import gr.aytn.vocabulary.adapter.VocabularyAdapter
import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository

class QuizActivity : AppCompatActivity() {

    var tvWord: TextView? = null
    var etTranslation: EditText? = null
    var btnSubmit: Button? = null
    private lateinit var quizViewModel: QuizViewModel
    private var mCurrentPosition:Int = 0
    private var mWordPairs:List<WordPair>?=null
    private var mCorrectAnswers : Int = 0
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
//        quizViewModel.getWordPairs().observe(this, Observer {
//            mWordPairs = it
//            Log.i("Quiz","$mWordPairs")
//        })

        tvWord = findViewById(R.id.quiz_word)
        etTranslation = findViewById(R.id.quiz_translation)
        btnSubmit = findViewById(R.id.submit)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)


        setQuestion()

        btnSubmit?.setOnClickListener {
            if (etTranslation?.text.toString() != "") {
                if(etTranslation?.text.toString() == mWordPairs?.get(mCurrentPosition)?.translation){
                    mCorrectAnswers++
                }
                mCurrentPosition++
                when {
                    mCurrentPosition < mWordPairs!!.size -> {
                        etTranslation?.setText("")
                        setQuestion()
                    }
                    else ->{
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("correct_answers",mCorrectAnswers)
                        intent.putExtra("total_questions",mWordPairs?.size)
                        startActivity(intent)
                        finish()
                    }

                }
            }
        }
    }
    private fun setQuestion(){
        quizViewModel.getWordPairs().observe(this, Observer {
            mWordPairs = it
            progressBar?.max = mWordPairs?.size!!
            tvProgress?.text = "${mCurrentPosition+1}/${progressBar?.max}"
//            if(mWordPairs!!.size != 0){
                val question = mWordPairs?.get(mCurrentPosition)
                progressBar?.progress = mCurrentPosition + 1
                tvWord?.text = question?.word
//            }else{
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
        })

    }
}