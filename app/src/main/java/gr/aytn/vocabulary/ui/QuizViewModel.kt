package gr.aytn.vocabulary.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.aytn.roomexample.WordPairDatabase
import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(val repository: WordPairRepository): ViewModel() {
    val readAllData: LiveData<List<WordPair>> = repository.readAllData

    fun getWordPairs(): LiveData<List<WordPair>> {
        return readAllData
    }

}