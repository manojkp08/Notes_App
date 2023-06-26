package com.example.notesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.Dao.NotesDao
import com.example.notesapp.Model.Notes

//Actually abstract classes cannot be instantiated

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase: RoomDatabase(){
    abstract fun myNotesDao(): NotesDao

    //companion object is just like static keyword in java.
    companion object
    {
        //Volatile means you can easily access it.
        @Volatile
        var INSTANCE:NotesDatabase? = null

        fun getDatabaseInstance(context: Context): NotesDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseinstance = Room.databaseBuilder(context, NotesDatabase::class.java, "Notes").allowMainThreadQueries().build()
                INSTANCE = roomDatabaseinstance
                return roomDatabaseinstance
            }
        }
    }

}