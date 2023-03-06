package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.infra.Constants
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding

class  MainActivity : AppCompatActivity(), View.OnClickListener
{
    private  lateinit var binding : ActivityMainBinding
    private var categoryId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()// esconde a barra de navegacao

        handleUserName()
        handleFilter(R.id.image_infinity)

        binding.buttonNewPhrase.setOnClickListener(this)
        //Images
        binding.imageHappyFace.setOnClickListener(this)
        binding.imageInfinity.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)

    }

    private fun handleUserName(){
        val name = SecurityPreferences(this).getString(Constants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase){
            var s = ""
        }else if (view.id in listOf(R.id.image_infinity, R.id.image_happy_face, R.id.image_sunny)){
            handleFilter(view.id)
        }
    }

    private fun handleFilter(id: Int) {
        binding.imageInfinity.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappyFace.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        when (id) {
            R.id.image_infinity -> {
                binding.imageInfinity.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = Constants.FILTER.INFINITY
            }
            R.id.image_happy_face -> {
                binding.imageHappyFace.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = Constants.FILTER.HAPPY_FACE
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = Constants.FILTER.SUNNY
            }
        }
    }
}