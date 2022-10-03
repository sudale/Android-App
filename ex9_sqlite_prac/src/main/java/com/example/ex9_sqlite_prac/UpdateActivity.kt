package com.example.ex9_sqlite_prac

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.ex9_sqlite_prac.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    lateinit var binding:ActivityUpdateBinding
    lateinit var todo:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        todo = intent.getStringExtra("todo").toString()
        binding.updateEditView.setText(todo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add , menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_add_save -> {
                val inputData = binding.updateEditView.text.toString()
                if (inputData.isBlank()){
                    //할일 문자열이 비어져 있을때
                    Toast.makeText(this,"할일이 비어져 있습니다. 확인 해주세요.",Toast.LENGTH_SHORT).show()
                    return false
                }else{
                    //할일 문자열이 있을때
                    val db = DBHelper(this).writableDatabase
                    db.execSQL("update TODO_TB set todo = ? where todo = ?", arrayOf(inputData,todo))
                    db.close()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}