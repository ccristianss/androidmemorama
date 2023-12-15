package dev.crsi.memorama.providers.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DBManager(context: Context) {
    private var dbHelper = DBHelper(context)
    private var db: SQLiteDatabase? = null

    fun openDbWrite() {
        if (db == null || !db!!.isOpen) {
            db = dbHelper.writableDatabase
        }
    }

    fun openDbRead() {
        if (db == null || !db!!.isOpen) {
            db = dbHelper.readableDatabase
        }
    }

    fun dbClose() {
        if (db != null && db!!.isOpen) {
            db?.close()
        }
    }

    fun dbInstance(): SQLiteDatabase {
        db = if (db == null || !db!!.isOpen) {
            dbHelper.writableDatabase
        } else {
            dbHelper.readableDatabase
        }
        return db!!
    }

}