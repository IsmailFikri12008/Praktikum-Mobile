package id.ac.unpas.mobcrafter.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.mobcrafter.model.Dosen
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

    // Dosen
    @Query("SELECT * FROM Dosen")
    fun loadAllDosen(): LiveData<List<Dosen>>

    @Query("SELECT * FROM Dosen WHERE id = :id")
    suspend fun findDosen(id: String): Dosen?

    @Query("SELECT * FROM Dosen ORDER BY nidn DESC")
    suspend fun getListDosen(): List<Dosen>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDosen(vararg items: Dosen)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDosen(items: List<Dosen>)

    @Delete
    fun deleteDosen(item: Dosen)

    @Query("DELETE FROM Dosen WHERE id = :id")
    suspend fun deleteDosen(id: String)

// Mahasiswa
    @Query("SELECT * FROM Mahasiswa")
    fun loadAllMahasiswa(): LiveData<List<Mahasiswa>>

    @Query("SELECT * FROM Mahasiswa WHERE id = :id")
    suspend fun findMahasiswa(id: String): Mahasiswa?

    @Query("SELECT * FROM Mahasiswa ORDER BY npm DESC")
    suspend fun getListMahasiswa(): List<Mahasiswa>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMahasiswa(vararg items: Mahasiswa)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMahasiswa(items: List<Mahasiswa>)

    @Delete
    fun deleteMahasiswa(item: Mahasiswa)

    @Query("DELETE FROM Mahasiswa WHERE id = :id")
    suspend fun deleteMahasiswa(id: String)
}