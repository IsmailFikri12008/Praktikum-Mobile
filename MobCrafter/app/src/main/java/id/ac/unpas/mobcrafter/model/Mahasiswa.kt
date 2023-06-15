package id.ac.unpas.mobcrafter.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mahasiswa(
    @PrimaryKey val id: String,
    val npm: String,
    val nama: String,
    val tanggal_lahir: String ,
    @ColumnInfo(name = "jenis_kelamin", defaultValue = "LAKI_LAKI") var jenis_kelamin: JenisKelamin,
)

enum class JenisKelamin {
    LAKI_LAKI,
    PEREMPUAN
}
