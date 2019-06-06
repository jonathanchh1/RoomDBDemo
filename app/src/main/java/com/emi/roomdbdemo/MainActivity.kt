package com.emi.roomdbdemo


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {


    private val RequestCodeData = 1
    private lateinit var toolbar : Toolbar
    private lateinit var viewModel: WordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordlistAdapter(this)
        recyclerview.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager

        viewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        viewModel.allwords.observe(this, Observer{
            word -> word?.let{
            adapter.setWord(it)
        }
            })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, RequestCodeData)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, Intentdata: Intent?) {
        super.onActivityResult(requestCode, resultCode, Intentdata)

        if(requestCode == RequestCodeData && resultCode == Activity.RESULT_OK){

            Intentdata?.let{
                data ->
                val word = Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY))
                viewModel.insert(word)
            }
        }
    }

}
