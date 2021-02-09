package id.rama.weatherapps.view.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.rama.weatherapps.view.model.UserModel

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userModel: UserModel)


    @Query("SELECT * FROM user_table")
    fun getData(): LiveData<List<UserModel>>
}