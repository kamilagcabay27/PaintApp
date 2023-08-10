package com.kamilagcabay.paintapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kamilagcabay.paintapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var canvasView: CanvasView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        canvasView =binding.canvasView

        binding.apply {
            colorButton.setOnClickListener {
                canvasView.setColor()
            }
            clearButton.setOnClickListener {
                canvasView.clearCanvas()
            }
        }




    }
}