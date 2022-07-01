package gr.aytn.vocabulary.domain

import javax.inject.Inject

data class WordPairUseCases @Inject constructor(
    val addWordPairUseCase: AddWordPairUseCase,
    val deleteWordPairUseCase: DeleteWordPairUseCase,
    val deleteAllWordPairsUseCase: DeleteAllWordPairsUseCase
        )