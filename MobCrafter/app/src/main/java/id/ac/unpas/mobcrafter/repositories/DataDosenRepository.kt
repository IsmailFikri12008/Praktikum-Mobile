package id.ac.unpas.mobcrafter.repositories

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.mobcrafter.model.DataDosen
import id.ac.unpas.mobcrafter.networks.DataDosenApi
import id.ac.unpas.mobcrafter.persistences.DataDosenDao
import javax.inject.Inject

class DataDosenRepository @Inject constructor(
    private val api: DataDosenApi,
    private val dao: DataDosenDao
) : Repository {
    suspend fun loadItems(
        onSuccess: (List<DataDosen>) -> Unit,
        onError: (List<DataDosen>, String) -> Unit
    ) {
        val list: List<DataDosen> = dao.getList()
        api.all()
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                data.whatIfNotNull {
                    it.data?.let { list ->
                        dao.insertAll(list)
                        val items: List<DataDosen> =
                            dao.getList()
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
        nidn: String,
        nama: String,
        gelarDepan: String,
        gelarBelakang: String,
        onSuccess: (DataDosen) -> Unit,
        onError: (DataDosen?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = DataDosen(id, nidn, nama, gelarDepan, gelarBelakang)
        dao.insertAll(item)
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
        nidn: String,
        nama: String,
        gelarDepan: String,
        gelarBelakang: String,
        onSuccess: (DataDosen) -> Unit,
        onError: (DataDosen?, String) -> Unit
    ) {
        val item = DataDosen(id, nidn, nama, gelarDepan, gelarBelakang)
        dao.insertAll(item)
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
        dao.delete(id)
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

    suspend fun find(id: String) : DataDosen? {
        return dao.find(id) }
}
