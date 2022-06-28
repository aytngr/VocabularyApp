package gr.aytn.vocabulary.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gr.aytn.roomexample.WordPairDao
import gr.aytn.roomexample.WordPairDatabase
import gr.aytn.vocabulary.repository.WordPairRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ) = Room.databaseBuilder(app, WordPairDatabase::class.java, "wordpair_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDao(db: WordPairDatabase) = db.wordPairDao()

    @Provides
    fun provideRepository(dao: WordPairDao) = WordPairRepository(dao)

    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope