package com.example.ex8_component

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ex8_component.databinding.ActivityMainBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    var cnt = 0
    override fun onDestroy() {
        // 액티비티가 종료(비활성화) 될때
        Log.d("myLog","onDestroy")
        super.onDestroy()
    }

    override fun onStop() {
        // 액티비티가 비활성화 될때 onPause 보다 뒤에 실행
        super.onStop()
        Log.d("myLog","onStop")
    }

    override fun onPause() {
        // 액티비티가 일시정지 상태
        Log.d("myLog","onPause")
        super.onPause()
    }

    override fun onStart() {
        // 액티비티가 실행(재실행) 될때 1
        Log.d("myLog","onStart")
        super.onStart()
    }

    override fun onResume() {
        // 액티비티가 실행(재실행) 될때 2 - 실행 시킨 이후
        Log.d("myLog","onResume")
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        /// 앱 실해시 최초 한번만 호출
        Log.d("myLog","onCreate")
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데이터 다시 돌려받을때 2
        val requestLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            val resultDate = it.data?.getStringExtra("result")
            Log.d("myLog","데이터 다시 돌려받을때 2 : ${resultDate}")
        }

        binding.goTwo.setOnClickListener {
            // 인텐트 사용 기본
            val intent = Intent(this, TwoActivity::class.java)
//            startActivity(intent)

            // 인텐트에 엑스트라 데이터 추가
//            val intent = Intent(this, TwoActivity::class.java)
//            intent.putExtra("data1", "엑스트라 데이터")
//            intent.putExtra("data2", 22)
//            startActivity(intent)

            // 데이터를 다시 돌려 받을때 1:
//            intent.putExtra("data1","데이터 돌려받기")
//            intent.putExtra("data2","1")
//            startActivityForResult(intent, 20)

            // 데이터를 다시 돌려받을때 2:
            intent.putExtra("data1", "엑스트라 데이터")
            intent.putExtra("data2", 1)
            requestLauncher.launch(intent)
        }

        // 액티비티 상태값 저장
        val data = savedInstanceState?.getInt("cnt")
        if (data != null){
            cnt = data
            binding.cntText.text = cnt.toString()
        }
        binding.cntBtn.setOnClickListener {
            cnt += 1
            binding.cntText.text = cnt.toString()
        }
    }
    // 데이터를 다시 돌려받을때 1
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 20 && resultCode == Activity.RESULT_OK){
            val result = data?.getStringExtra("result")
            Log.d("myLog","데이터 돌려받기 1 : ${result}")
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // 번들 객체에 저장된 데이터 가져오기
        cnt = savedInstanceState.getInt("cnt")
        Log.d("myLog","번들객체에 저장된 데이터 : ${cnt}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 번들 객체에 데이터 저장
        outState.putInt("cnt",cnt)
    }
}