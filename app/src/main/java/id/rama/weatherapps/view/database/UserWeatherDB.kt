package id.rama.weatherapps.view.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.rama.weatherapps.view.dao.UserDao
import id.rama.weatherapps.view.model.UserModel

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class UserWeatherDB: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE : UserWeatherDB? = null

        fun getDatabase(context: Context):UserWeatherDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserWeatherDB::class.java,
                    "weather_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}