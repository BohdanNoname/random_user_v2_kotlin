package com.nedashkivskyi.randomuser.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nedashkivskyi.randomuser.App
import com.nedashkivskyi.randomuser.R
import com.nedashkivskyi.randomuser.databinding.ActivityMainBinding
import com.nedashkivskyi.randomuser.db.AppDatabase
import com.nedashkivskyi.randomuser.di.subcomponent.MainSubcomponent

class MainActivity : AppCompatActivity() {

    lateinit var appComponent: MainSubcomponent
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (applicationContext as App).appComponent.mainSubcomponent.create()
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}