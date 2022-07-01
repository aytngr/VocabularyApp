package gr.aytn.vocabulary.domain

import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository
import javax.inject.Inject

class DeleteWordPairUseCase @Inject constructor(
    private val repository: WordPairRepository
) {
    operator fun invoke(wordpair: WordPair){
        return repository.deleteWordPair(wordpair)
    }
}