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

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.menu_add_save ->{
            // 데이터 베이스에 저장 및 intent에 저장한 데이터 담아서 화면에 전달
            val inputData = binding.addEditView.text.toString()
            val db = DBHelper(this).writableDatabase
            db.execSQL(
                "insert into TODO_TB(todo)values(?)",
                arrayOf<String>(inputData)
            )
            db.close()
            val intent = intent
            intent.putExtra("result",inputData)
            setResult(Activity.RESULT_OK,intent)
            finish()
            true
        }
        else -> true
    }
}