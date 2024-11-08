package com.example.emergency.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class EventRepository(context: Context) {

    private val dbHelper = DBHelper(context)

    fun insertEvent(event: Event) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DBHelper.COLUMN_ID, event.id)
            put(DBHelper.COLUMN_TITLE, event.title)
            put(DBHelper.COLUMN_DESCRIPTION, event.description)
            put(DBHelper.COLUMN_DATE, event.date)
            put(DBHelper.COLUMN_PHOTO_PATH, event.photoPath)
        }
        db.insert(DBHelper.TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getAllEvents(): List<Event> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DBHelper.TABLE_NAME,
            null,
            null,
            null,
            null, null, null
        )

        val events = mutableListOf<Event>()

        cursor.use {
            while (cursor.moveToNext()) {
                val event = Event(
                    cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DATE)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PHOTO_PATH))
                )
                events.add(event)
            }
        }

        db.close()
        return events
    }
}