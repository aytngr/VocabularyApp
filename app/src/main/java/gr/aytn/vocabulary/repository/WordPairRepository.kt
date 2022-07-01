package gr.aytn.vocabulary.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import gr.aytn.roomexample.WordPairDao
import gr.aytn.vocabulary.model.WordPair
import javax.inject.Inject

class WordPairRepository @Inject constructor(private val wordpairDao: WordPairDao) {

    val readAllData: LiveData<List<WordPair>> = wordpairDao.getAll()
    val count: LiveData<Int> = wordpairDao.count()

    fun addWordPair(wordpair: WordPair) {
        wordpairDao.addWordPair(wordpair)
    }
    fun deleteWordPair(wordpair: WordPair) {
        wordpairDao.deleteWordPair(wordpair)
    }
    fun deleteAll() {
        wordpairDao.deleteAll()
    }

}