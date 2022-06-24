package gr.aytn.vocabulary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordPair (

    @ColumnInfo(name = "word") val word: String?,
    @ColumnInfo(name = "translation") val translation: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0)
