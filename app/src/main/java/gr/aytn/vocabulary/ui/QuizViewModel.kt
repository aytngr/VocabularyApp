package gr.aytn.vocabulary.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import gr.aytn.roomexample.WordPairDatabase
import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository

class QuizViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<WordPair>>
    private val repository: WordPairRepository
    private val db : WordPairDatabase

    init {
        db = WordPairDatabase.getDatabase(application)
        val wordPairDao = db.wordPairDao()
        repository = WordPairRepository(wordPairDao)
        readAllData = repository.readAllData
    }
    fun getWordPairs(): LiveData<List<WordPair>> {
        return readAllData
    }

}