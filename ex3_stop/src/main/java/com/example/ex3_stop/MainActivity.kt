package com.example.ex3_stop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import com.example.ex3_stop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 스탑워치를 멈춘시간
    var pauseTime = 0L
    // 뒤로가기버튼의 시간 계산용
    var initTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 스탑워치 기능 구현
        // 시작버튼
        binding.startBtn.setOnClickListener {
            // 스탑워치 시간 = 부팅부터 현재까지 시간 + 멈춘시간
            binding.chronometer.base = SystemClock.elapsedRealtime()+pauseTime
            binding.chronometer.start()
            // 버튼의 표시조정
            binding.stopBtn.isEnabled=true
            binding.resetBtn.isEnabled=true
            binding.startBtn.isEnabled=false
        }
        // 스탑버튼
        binding.stopBtn.setOnClickListener {
            // 멈춘시간 = 스탑워치 시간 - SystemClock.elpasedRealtime()
            pauseTime = binding.chronometer.base-SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.stopBtn.isEnabled=false
            binding.resetBtn.isEnabled=true
            binding.startBtn.isEnabled=true
        }
        // 리셋버튼
        binding.resetBtn.setOnClickListener {
            // 멈춘시간 초기화
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.stopBtn.isEnabled=false
            binding.resetBtn.isEnabled=false
            binding.startBtn.isEnabled=true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode === KeyEvent.KEYCODE_BACK){
            // 뒤로가기버튼을 처음 눌렀거나, 누른지 3초가 지났을때
            if (System.currentTimeMillis() - initTime > 3000){
                Toast.makeText(this,"종료하려면 한 번 더 누르세요!!", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}