package com.example.flashcards

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flashcards.Flashcard
import com.example.flashcards.FlashcardDao

@Database(entities = [Flashcard::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun flashcardDao(): FlashcardDao
}
