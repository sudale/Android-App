package com.example.ex9_sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,"testdb",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        // 앱이 설치된 후 SQLiteOpenHelper 클래스가 이용되는 순간 한번 호출
//                db?.execSQL("create table USER_DB(" +
//                "_id integer primary key autoincrement," +
//                "name not null," +
//                "phone)")
                db?.execSQL("create table USER_DB(" +
                "_id," +
                "name not null," +
                "phone)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // 생성자에게 지정한 DB버전 정보가 변경 될때 마다 호출
    }
}