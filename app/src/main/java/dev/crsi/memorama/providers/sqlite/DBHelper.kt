package dev.crsi.memorama.providers.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dev.crsi.memorama.helpers.Constants.Companion.NAME_DATA_BASE
import dev.crsi.memorama.helpers.Constants.Companion.VERSION_DATABASE
import dev.crsi.memorama.providers.sqlite.tables.ScoresTbl.Companion.CREATE_SCORES_TABLE
import dev.crsi.memorama.providers.sqlite.tables.ScoresTbl.Companion.DROP_SCORES_TABLE

class DBHelper(context: Context) : SQLiteOpenHelper(
    context,
    NAME_DATA_BASE,
    null,
    VERSION_DATABASE
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_SCORES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(DROP_SCORES_TABLE)
        onCreate(db)
    }
}