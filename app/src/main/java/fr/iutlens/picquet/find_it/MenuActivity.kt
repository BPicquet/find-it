package fr.iutlens.picquet.find_it

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
    fun onClickBtnPlay(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}