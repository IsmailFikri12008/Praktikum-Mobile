package id.ac.unpas.mobcrafter.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.mobcrafter.model.Matakuliah

@Dao
interface MatakuliahDao {
    @Query("SELECT * FROM Matakuliah")
    fun loadAll(): LiveData<List<Matakuliah>>

    @Query("SELECT * FROM Matakuliah WHERE id = :id")
    suspend fun find(id: String): Matakuliah?

    @Query("SELECT * FROM Matakuliah ORDER BY kode DESC")
    suspend fun getList(): List<Matakuliah>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Matakuliah)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Matakuliah>)

    @Delete
    fun delete(item: Matakuliah)

    @Query("DELETE FROM Matakuliah WHERE id = :id")
    suspend fun delete(id: String)
}