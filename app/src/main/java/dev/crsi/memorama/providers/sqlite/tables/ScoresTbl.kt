package dev.crsi.memorama.providers.sqlite.tables

import dev.crsi.memorama.helpers.Constants.Companion.ID
import dev.crsi.memorama.helpers.Constants.Companion.NAME_TABLE_SCORES
import dev.crsi.memorama.helpers.Constants.Companion.SCORE
import dev.crsi.memorama.helpers.Constants.Companion.USERNAME

class ScoresTbl {

    companion object {

        const val CREATE_SCORES_TABLE = " CREATE TABLE $NAME_TABLE_SCORES " +
                " ( $ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " $USERNAME TEXT NOT NULL," +
                " $SCORE TEXT NOT NULL )"

        const val SELECT_SCORES_TABLE =
            " SELECT $USERNAME, $SCORE FROM $NAME_TABLE_SCORES ORDER BY $SCORE ASC"

        const val DROP_SCORES_TABLE = "DROP TABLE IF EXISTS $NAME_TABLE_SCORES"
    }

}