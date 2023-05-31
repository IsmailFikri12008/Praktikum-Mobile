package id.ac.unpas.mobcrafter.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.mobcrafter.model.Mahasiswa
import id.ac.unpas.mobcrafter.model.Matakuliah

@Dao
interface PerkuliahanDao {
    // Matakuliah
    @Query("SELECT * FROM Matakuliah")
    fun loadAllMatakuliah(): LiveData<List<Matakuliah>>

    @Query("SELECT * FROM Matakuliah WHERE id = :id")
    suspend fun findMatakuliah(id: String): Matakuliah?

    @Query("SELECT * FROM Matakuliah ORDER BY kode DESC")
    suspend fun getListMatakuliah(): List<Matakuliah>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMatakuliah(vararg items: Matakuliah)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMatakuliah(items: List<Matakuliah>)

    @Delete
    fun deleteMatakuliah(item: Matakuliah)

    @Query("DELETE FROM Matakuliah WHERE id = :id")
    suspend fun deleteMatakuliah(id: String)


//    // Mahasiswa
//    @Query("SELECT * FROM Mahasiswa")
//    fun loadAllMahasiswa(): LiveData<List<Mahasiswa>>
//
//    @Query("SELECT * FROM Mahasiswa WHERE id = :id")
//    suspend fun find(id: String): Mahasiswa?
//
//    @Query("SELECT * FROM Mahasiswa ORDER BY kode DESC")
//    suspend fun getList(): List<Mahasiswa>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(vararg items: Mahasiswa)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(items: List<Mahasiswa>)
//
//    @Delete
//    fun delete(item: Mahasiswa)
//
//    @Query("DELETE FROM Mahasiswa WHERE id = :id")
//    suspend fun delete(id: String)
}