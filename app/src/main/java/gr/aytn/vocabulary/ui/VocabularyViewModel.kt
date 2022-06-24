package gr.aytn.vocabulary.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import gr.aytn.roomexample.WordPairDatabase
import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VocabularyViewModel(application: Application): AndroidViewModel(application){

    private var mutableLiveData = MutableLiveData<List<WordPair>>()
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
    fun addWordPair(wordpair: WordPair){
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("refd","$wordpair")
            repository.addWordPair(wordpair)
        }
    }
    fun deleteWordPair(wordpair: WordPair){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWordPair(wordpair)
        }
    }
    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            db.clearAllTables()
        }
    }




//    private var mutableLiveData = MutableLiveData<ArrayList<WordPair>>()
//    var data = ArrayList<WordPair>()
//    fun addWordPair(word:String,translation: String){
//        data.add(0, WordPair(word, translation))
//    }
//    fun getHistoryItemList(): LiveData<ArrayList<WordPair>> {
//        if (mutableLiveData.value == null){
//            mutableLiveData.value = data
//        }
//        return mutableLiveData
//    }
}