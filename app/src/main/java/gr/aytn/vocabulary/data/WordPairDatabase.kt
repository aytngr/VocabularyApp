package gr.aytn.roomexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gr.aytn.vocabulary.model.WordPair

@Database(entities = [WordPair::class], version = 2)
abstract class WordPairDatabase : RoomDatabase() {
    abstract fun wordPairDao(): WordPairDao

    companion object{
        @Volatile
        private var INSTANCE: WordPairDatabase? = null
        fun getDatabase(context: Context): WordPairDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordPairDatabase::class.java,
                        "wordpair_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
            return instance
            }
        }
    }
}
