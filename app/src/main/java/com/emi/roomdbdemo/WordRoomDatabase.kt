package com.emi.roomdbdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao() : WordDao


     companion object{
         @Volatile
         private var INSTANCE : WordRoomDatabase?=null

         fun getDatabase(context : Context, scope : CoroutineScope) :
                 WordRoomDatabase{

             return INSTANCE?: synchronized(this){

                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     WordRoomDatabase::class.java,
                     "word_database"
                 )

                     .fallbackToDestructiveMigration()
                     .addCallback(WordRoomDatabaseCallback(scope))
                     .build()

                 INSTANCE = instance
                 instance
             }
         }


         private class WordRoomDatabaseCallback(private val scope : CoroutineScope) : RoomDatabase.Callback(){

             override fun onOpen(db: SupportSQLiteDatabase) {
                 super.onOpen(db)

                 INSTANCE?.let {
                     database ->
                     scope.launch(Dispatchers.IO){
                         populateDatabase(database.wordDao())
                     }
                 }
             }

         }

         suspend fun populateDatabase(wordDao : WordDao){

             wordDao.deleteAll()

             var word = Word("hello")
             wordDao.insertAll(word)
             word = Word("World!")
             wordDao.insertAll(word)
         }
     }
}