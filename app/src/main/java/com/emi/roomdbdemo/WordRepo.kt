package com.emi.roomdbdemo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WordRepo(private val wordDao: WordDao) {


   val allwords : LiveData<List<Word>> = wordDao.getAlphabetizedWords()


    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insertAll(word)
    }
}