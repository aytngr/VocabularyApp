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

    fun addWordPair(user: WordPair) {
        wordpairDao.addWordPair(user)
    }
    fun deleteWordPair(user: WordPair) {
        wordpairDao.deleteWordPair(user)
    }
    fun deleteAll() {
        wordpairDao.deleteAll()
    }

}