package com.emi.roomdbdemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

     val allwords : LiveData<List<Word>>
     val wordRepo : WordRepo


    init {

        val wordDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
         wordRepo = WordRepo(wordDao)
         allwords = wordRepo.allwords

    }


     fun insert(word : Word) = viewModelScope.launch(Dispatchers.IO){
        wordRepo.insert(word)
    }
}