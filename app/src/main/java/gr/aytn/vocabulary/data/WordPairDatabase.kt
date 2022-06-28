package gr.aytn.roomexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gr.aytn.vocabulary.model.WordPair

@Database(entities = [WordPair::class], version = 2)
abstract class WordPairDatabase : RoomDatabase() {
    abstract fun wordPairDao(): WordPairDao
}
