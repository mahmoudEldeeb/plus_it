package com.fudex.plusit.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.fudex.plusit.R
import com.fudex.plusit.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var  binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.splash)
        binding.logo.startAnimation(slideAnimation)

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}