package com.example.ex9_sqlite_prac

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ex9_sqlite_prac.databinding.ItemRecyclerviewBinding


class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    lateinit var db: DBHelper
    lateinit var context: Context
    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    context = parent.context
    db = DBHelper(context)
       return MyViewHolder(ItemRecyclerviewBinding.inflate(
           LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding
        binding.itemData.text= datas!![position]

        // 삭제 이벤트 추가
        binding.delBtn.setOnClickListener {
            try {
                val data = binding.itemData.text.toString()
                val delDB = db.writableDatabase
                delDB.execSQL("delete from TODO_TB where todo = ?", arrayOf(data))
                delDB.close()

                // 삭제후 화면 갱신
                datas.remove(datas!![position])
                notifyDataSetChanged()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        // 수정 이벤트 추가
        binding.itemData.setOnClickListener {
            if (binding.updateBtn.visibility == View.GONE){
                binding.updateBtn.visibility = View.VISIBLE
                binding.updateBtn.setOnClickListener {
                    val intent = Intent(binding.itemData.context, UpdateActivity::class.java)
                    intent.putExtra("todo",binding.itemData.text.toString())
                    startActivity(context, intent, null)
                }
            }else{
                binding.updateBtn.visibility = View.GONE
            }
        }
    }
}
