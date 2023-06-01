package id.ac.unpas.mobcrafter.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Dosen(
    @PrimaryKey val id: String,
    val nidn:String,
    val nama:String,
    val gelar_depan:String,
    val gelar_belakang:String,
    val pendidikan: Pendidikan
)

enum class Pendidikan {
    S2,
    S3
}



