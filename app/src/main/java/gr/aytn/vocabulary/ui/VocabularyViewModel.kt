package gr.aytn.vocabulary.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.aytn.roomexample.WordPairDatabase
import gr.aytn.vocabulary.domain.WordPairUseCases
import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VocabularyViewModel @Inject constructor(private val repository: WordPairRepository,
                                              private val wordPairUseCases: WordPairUseCases): ViewModel(){

    val readAllData: LiveData<List<WordPair>> = repository.readAllData

    fun getWordPairs(): LiveData<List<WordPair>> {
        return readAllData
    }
    fun addWordPair(wordpair: WordPair){
        viewModelScope.launch(Dispatchers.IO) {
            wordPairUseCases.addWordPairUseCase(wordpair)
        }
    }
    fun deleteWordPair(wordpair: WordPair){
        viewModelScope.launch(Dispatchers.IO) {
            wordPairUseCases.deleteWordPairUseCase(wordpair)
        }
    }
    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("viewmodel","deleted")
            wordPairUseCases.deleteAllWordPairsUseCase()
        }
    }

}