package com.example.lesson1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var mainBinding: ActivityMainBinding

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        val listener = View.OnClickListener {
            val current = when (it.id) {
                R.id.btn_counter1 -> MainPresenter.Buttons.FIRST
                R.id.btn_counter2 -> MainPresenter.Buttons.SECOND
                R.id.btn_counter3 -> MainPresenter.Buttons.THIRD
                else -> throw IllegalArgumentException("")
            }
            presenter.counterClick(current)
        }
        mainBinding.btnCounter1.setOnClickListener(listener)
        mainBinding.btnCounter2.setOnClickListener(listener)
        mainBinding.btnCounter3.setOnClickListener(listener)
    }

    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> mainBinding.btnCounter1.text = text
            1 -> mainBinding.btnCounter2.text = text
            2 -> mainBinding.btnCounter3.text = text
        }
    }
}