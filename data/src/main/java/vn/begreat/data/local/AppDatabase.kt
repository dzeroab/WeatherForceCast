package vn.begreat.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao


    ///
    companion object {
        fun build(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "weather.db"
            ).build()
        }
    }
}