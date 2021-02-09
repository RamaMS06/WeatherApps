package id.rama.weatherapps.view.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserModel (
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val nama_panjang : String
)