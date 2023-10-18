package com.example.jetnotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetnotes.model.Note
import com.example.jetnotes.util.DateConverter
import com.example.jetnotes.util.UUIDConverter


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDoa() : NoteDatabaseDao
}