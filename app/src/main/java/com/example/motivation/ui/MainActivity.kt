package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.infra.Constants
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding
import java.util.*

class  MainActivity : AppCompatActivity(), View.OnClickListener
{
    private  lateinit var binding : ActivityMainBinding
    private var categoryId = Constants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()// esconde a barra de navegacao

        showUserPhrase()
        handleFilter(R.id.image_all)
        refrashPhrase()

        binding.buttonNewPhrase.setOnClickListener(this)
        //Images
        binding.imageHappyFace.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }


    private fun showUserPhrase(){
        val name = SecurityPreferences(this).getString(Constants.KEY.USER_NAME)
        val salutation = getString(R.string.hello)
        binding.textUserName.text = "$salutation, $name!"
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase){
            refrashPhrase()
        }else if (view.id in listOf(R.id.image_all, R.id.image_happy_face, R.id.image_sunny)){
            handleFilter(view.id)
        }else if (view.id == R.id.text_phrase){
        }
    }

    private fun refrashPhrase() {
        binding.textPhrase.text = Mock().getPhrase(categoryId, Locale.getDefault().language)
    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappyFace.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = Constants.FILTER.ALL
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