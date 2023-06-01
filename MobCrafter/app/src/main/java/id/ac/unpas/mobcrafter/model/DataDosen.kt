package id.ac.unpas.mobcrafter.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataDosen(
    @PrimaryKey val id: String,
    val nidn:String,
    val nama:String,
    val gelarDepan:String,
    val gelarBelakang:String
)