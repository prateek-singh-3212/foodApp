package com.example.math

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.math.databinding.ActivityLoginBinding
import com.example.math.utils.Constants
import com.example.math.utils.FoodSharedPreference


class LoginActivity : AppCompatActivity() {

    private lateinit var foodSharedPreference: FoodSharedPreference
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        foodSharedPreference = FoodSharedPreference(this)

        if (foodSharedPreference.getString(Constants.TOKEN) != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val imageStream = this.resources.openRawResource(R.raw.das)
        val bitmap = BitmapFactory.decodeStream(imageStream)
        binding.backGroundImage.setImageBitmap(bitmap)

        binding.loginButton.setOnClickListener {
            foodSharedPreference.setString(Constants.TOKEN, "bf7f2fedcf244505affa303532014b2f")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}