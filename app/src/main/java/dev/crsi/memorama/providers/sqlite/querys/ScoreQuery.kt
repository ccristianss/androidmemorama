package dev.crsi.memorama.providers.sqlite.querys

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import dev.crsi.memorama.helpers.Constants.Companion.NAME_TABLE_SCORES
import dev.crsi.memorama.helpers.Constants.Companion.SCORE
import dev.crsi.memorama.helpers.Constants.Companion.USERNAME
import dev.crsi.memorama.models.Score
import dev.crsi.memorama.providers.sqlite.DBManager
import dev.crsi.memorama.providers.sqlite.tables.ScoresTbl.Companion.SELECT_SCORES_TABLE

class ScoreQuery(context: Context) {
    val dbManager = DBManager(context)


    fun addScore(score: Score): Long {
        dbManager.openDbWrite()
        val db = dbManager.dbInstance()
        val contentValues = ContentValues()
        contentValues.put(USERNAME, score.username)
        contentValues.put(SCORE, score.score)
        val success = db.insert(NAME_TABLE_SCORES, null, contentValues)
        dbManager.dbClose()
        return success
    }

    @SuppressLint("Recycle")
    fun getScores(): List<Score> {
        return try {
            dbManager.openDbRead()
            val db = dbManager.dbInstance()

            val listScore: ArrayList<Score> = ArrayList()
            val cursor = db.rawQuery(SELECT_SCORES_TABLE, null)
            if (cursor.moveToFirst()) {
                do {
                    val score = Score()
                    score.username = cursor.getString(cursor.getColumnIndexOrThrow(USERNAME))
                    score.score = cursor.getString(cursor.getColumnIndexOrThrow(SCORE)).toInt()
                    listScore.add(score)
                } while (cursor.moveToNext())
            }
            listScore

        } catch (e: SQLiteException) {

            return arrayListOf()
        }
    }
}