package com.example.emergency.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Definir el nombre de la base de datos y la versión
    companion object {
        const val DATABASE_NAME = "emergency_db" // Nombre de la base de datos
        const val DATABASE_VERSION = 1 // Versión de la base de datos

        // Nombre de la tabla
        const val TABLE_NAME = "events"

        // Nombres de las columnas
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_DATE = "date"
        const val COLUMN_PHOTO_PATH = "photoPath"
    }

    // Método para crear la tabla
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID TEXT PRIMARY KEY,
                $COLUMN_TITLE TEXT,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_DATE TEXT,
                $COLUMN_PHOTO_PATH TEXT
            );
        """
        db?.execSQL(createTableQuery)
    }

    // Método para actualizar la base de datos (por ejemplo, cuando cambias la estructura)
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Aquí puedes manejar la migración de la base de datos si es necesario
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}
