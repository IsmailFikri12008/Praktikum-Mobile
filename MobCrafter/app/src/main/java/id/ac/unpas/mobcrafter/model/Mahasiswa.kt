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
    val jenis_kelamin: String
)

enum class JenisKelamin(var value:String) {
    LAKI_LAKI("Laki-laki"),
    PEREMPUAN("Perempuan")
}
