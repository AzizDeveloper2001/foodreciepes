package uz.pdp.foodrecipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

import uz.pdp.foodrecipes.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.text.setTypeface(FontManager.getInstance(getAssets()).getFont("fonts/font-name.ttf"));
// be sure to set custom typeface before setting the animate type, otherwise the font may not be updated.

        binding.text.animateText("Taomlar retseptlari") // animate
        var countDownTimer=object :CountDownTimer(2000,100){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                val intent=Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(intent)
                finish()

            }

        }
        countDownTimer.start()
    }
}