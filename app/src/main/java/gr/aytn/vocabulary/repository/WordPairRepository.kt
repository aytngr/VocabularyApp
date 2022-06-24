package gr.aytn.vocabulary.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import gr.aytn.roomexample.WordPairDao
import gr.aytn.vocabulary.model.WordPair

class WordPairRepository(private val userDao: WordPairDao) {

    val readAllData: LiveData<List<WordPair>> = userDao.getAll()

    fun addWordPair(user: WordPair) {
        userDao.addWordPair(user)
    }
    fun deleteWordPair(user: WordPair) {
        userDao.deleteWordPair(user)
    }

}