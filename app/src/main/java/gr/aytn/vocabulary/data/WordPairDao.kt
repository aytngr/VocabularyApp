package gr.aytn.roomexample

import androidx.lifecycle.LiveData
import androidx.room.*
import gr.aytn.vocabulary.model.WordPair

@Dao
interface WordPairDao {
    @Query("SELECT * FROM wordpair ORDER BY id DESC")
    fun getAll(): LiveData<List<WordPair>>

    @Query("SELECT COUNT(*) FROM wordpair")
    fun count(): LiveData<Int>

    @Query("DELETE FROM wordpair")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE) // <- Annotate the 'addUser' function below. Set the onConflict strategy to IGNORE so if exactly the same user exists, it will just ignore it.
    fun addWordPair(wordpair: WordPair)

    @Delete
    fun deleteWordPair(wordpair: WordPair)
}
