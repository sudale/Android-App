package com.example.ex9_sqlite_prac

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ex9_sqlite_prac.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_add_save -> {
                val intent = intent
                val todoText = binding.addEditView.text.toString()

                if(todoText.isBlank()){
                    //할일 입력값이 없을때
                    setResult(Activity.RESULT_CANCELED, intent)
                }else{
                    //할일 입력값이 있을때
                    //데이터 베이스 연동해서 inser문 작성
                    val db = DBHelper(this).writableDatabase
                    db.execSQL("insert into TODO_TB(todo) values(?)", arrayOf(todoText))
                    db.close()
                    intent.putExtra("result",todoText)
                    setResult(Activity.RESULT_OK,intent)
                }
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}