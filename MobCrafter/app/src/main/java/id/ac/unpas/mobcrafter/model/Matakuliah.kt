package id.ac.unpas.mobcrafter.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Matakuliah(
    @PrimaryKey val id: String,
    val kode: String,
    val nama: String,
    val sks: Byte,
    val praktikum: Boolean,
    val deskripsi: String
)
