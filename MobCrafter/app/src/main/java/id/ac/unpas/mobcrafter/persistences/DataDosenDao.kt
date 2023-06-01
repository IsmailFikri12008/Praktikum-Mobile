package id.ac.unpas.mobcrafter.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.mobcrafter.model.DataDosen

@Dao
interface DataDosenDao {
    @Query("SELECT * FROM DataDosen")
    fun loadAll(): LiveData<List<DataDosen>>

    @Query("SELECT * FROM DataDosen WHERE id = :id")
    suspend fun find(id: String): DataDosen?

    @Query("SELECT * FROM DataDosen ORDER BY nidn DESC")
    suspend fun getList(): List<DataDosen>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: DataDosen)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<DataDosen>)

    @Delete
    fun delete(item: DataDosen)

    @Query("DELETE FROM DataDosen WHERE id = :id")
    fun delete(id: String)


}