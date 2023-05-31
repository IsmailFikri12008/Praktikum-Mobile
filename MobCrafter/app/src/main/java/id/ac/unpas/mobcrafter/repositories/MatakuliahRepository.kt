package id.ac.unpas.mobcrafter.repositories

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.mobcrafter.model.Matakuliah
import id.ac.unpas.mobcrafter.networks.MatakuliahApi
import id.ac.unpas.mobcrafter.persistences.PerkuliahanDao
import javax.inject.Inject

class MatakuliahRepository @Inject constructor(
    private val api: MatakuliahApi,
    private val dao: PerkuliahanDao
) : Repository {
    suspend fun loadItems(
        onSuccess: (List<Matakuliah>) -> Unit,
        onError: (List<Matakuliah>, String) -> Unit
    ) {
        val list: List<Matakuliah> = dao.getListMatakuliah()
        api.all()
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                data.whatIfNotNull {
                    it.data?.let { list ->
                        dao.insertAllMatakuliah(list)
                        val items: List<Matakuliah> =
                            dao.getListMatakuliah()
                        onSuccess(items)
                    }
                }
            }
// handle the case when the API request gets an error response.
// e.g. internal server error.
            .suspendOnError {
                onError(list, message())
            }
            // handle the case when the API request gets an exception response.
            // e.g. network connection error.
            .suspendOnException {
                onError(list, message())
            }
    }

    suspend fun insert(
        kode: String,
        nama: String,
        sks: Byte,
        praktikum: Boolean,
        deskripsi: String,
        onSuccess: (Matakuliah) -> Unit,
        onError: (Matakuliah?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = Matakuliah(id, kode, nama, sks, praktikum, deskripsi)
        dao.insertAllMatakuliah(item)
        api.insert(item)
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                onSuccess(item)
            }
            // handle the case when the API request gets an error response.
            // e.g. internal server error.
            .suspendOnError {
                onError(item, message())
            }
            // handle the case when the API request gets an exception response.
            // e.g. network connection error.
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun update(
        id: String,
        kode: String,
        nama: String,
        sks: Byte,
        praktikum: Boolean,
        deskripsi: String,
        onSuccess: (Matakuliah) -> Unit,
        onError: (Matakuliah?, String) -> Unit
    ) {
        val item = Matakuliah(id, kode, nama, sks, praktikum, deskripsi)
        dao.insertAllMatakuliah(item)
        api.update(id, item)
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                onSuccess(item)
            }
            // handle the case when the API request gets an error response.
            // e.g. internal server error.
            .suspendOnError {
                onError(item, message())
            }
            // handle the case when the API request gets an exception response.
            // e.g. network connection error.
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun delete(
        id: String, onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        dao.deleteMatakuliah(id)
        api.delete(id)
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                data.whatIfNotNull {
                    onSuccess()
                }
            }
            // handle the case when the API request gets an error response.
            // e.g. internal server error.
            .suspendOnError {
                onError(message())
            }
// handle the case when the API request gets an exception response.
// e.g. network connection error.
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun find(id: String) : Matakuliah? {
        return dao.findMatakuliah(id)
    }
}