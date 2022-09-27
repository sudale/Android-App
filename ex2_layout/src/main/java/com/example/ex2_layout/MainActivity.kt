package com.example.ex2_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ex2_layout.databinding.ActivityMainBinding
import com.example.ex2_layout.databinding.FrameTestBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FrameTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // FrameLayout에서 visibility속성을 활용한 예시
        binding.btn.setOnClickListener {
            binding.btn.visibility = View.GONE
            binding.img.visibility = View.VISIBLE
        }
        binding.img.setOnClickListener {
            binding.img.visibility = View.GONE
            binding.btn.visibility = View.VISIBLE
        }
    }
}