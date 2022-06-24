package gr.aytn.vocabulary.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import gr.aytn.roomexample.WordPairDatabase
import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository: WordPairRepository
    private val db : WordPairDatabase
    init {
        db = WordPairDatabase.getDatabase(application)
        val wordPairDao = db.wordPairDao()
        repository = WordPairRepository(wordPairDao)
    }
//    fun count(): Int = runBlocking{
//        return@runBlocking repository.count()
//    }
}