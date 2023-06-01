package id.ac.unpas.mobcrafter.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.mobcrafter.model.Matakuliah

@Database(entities = [Matakuliah::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun matakuliahDao(): MatakuliahDao
    abstract fun dataDosenDao(): DataDosenDao
}



    abstract fun perkuliahanDao(): PerkuliahanDao
}

