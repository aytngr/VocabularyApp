package gr.aytn.vocabulary.domain

import gr.aytn.vocabulary.model.WordPair
import gr.aytn.vocabulary.repository.WordPairRepository
import javax.inject.Inject

class AddWordPairUseCase @Inject constructor(
    private val repository: WordPairRepository
) {
    operator fun invoke(wordpair: WordPair){
        return repository.addWordPair(wordpair)
    }
}