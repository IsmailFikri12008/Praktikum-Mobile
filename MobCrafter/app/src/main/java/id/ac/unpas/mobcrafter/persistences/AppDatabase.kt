package id.ac.unpas.mobcrafter.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.mobcrafter.model.Dosen
import id.ac.unpas.mobcrafter.model.Mahasiswa
import id.ac.unpas.mobcrafter.model.Matakuliah

@Database(entities = [Matakuliah::class, Dosen::class, Mahasiswa::class], version = 14)
abstract class AppDatabase : RoomDatabase() {
    abstract fun perkuliahanDao(): PerkuliahanDao
}


