package com.emi.roomdbdemo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {


    @Query("Select * from word_table ORDER BY word ASC")
     fun getAlphabetizedWords() : LiveData<List<Word>>

    @Insert
    suspend fun insertAll(word : Word)

    @Query("DELETE from word_table")
    fun deleteAll()

}

