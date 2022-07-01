package gr.aytn.vocabulary.domain

import android.util.Log
import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository
import javax.inject.Inject

class DeleteAllWordPairsUseCase @Inject constructor(
    private val repository: WordPairRepository
) {
    operator fun invoke(){
        Log.i("deleteall","deleted")
        return repository.deleteAll()
    }
}