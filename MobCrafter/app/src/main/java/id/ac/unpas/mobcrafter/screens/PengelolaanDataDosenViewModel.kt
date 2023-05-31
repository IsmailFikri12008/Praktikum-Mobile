package id.ac.unpas.mobcrafter.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.mobcrafter.model.DataDosen
import id.ac.unpas.mobcrafter.repositories.DataDosenRepository
import javax.inject.Inject

@HiltViewModel
class PengelolaanDataDosenViewModel @Inject constructor(
    private val
    dataDosenRepository: DataDosenRepository
) : ViewModel() {
    private val _isLoading: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _success: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success
    private val _toast: MutableLiveData<String> =
        MutableLiveData()
    val toast: LiveData<String> get() = _toast
    private val _list: MutableLiveData<List<DataDosen>> =
        MutableLiveData()
    val list: LiveData<List<DataDosen>> get() = _list
    suspend fun loadItems() {
        _isLoading.postValue(true)
        dataDosenRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }

    suspend fun insert(
        nidn: String,
        nama: String,
        gelarDepan: String,
        gelarBelakang: String
    ) {
        _isLoading.postValue(true)
        dataDosenRepository.insert(nidn, nama, gelarDepan, gelarBelakang,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }

    suspend fun loadItem(
        id: String, onSuccess: (DataDosen?
        ) -> Unit) {
        val item = dataDosenRepository.find(id)
        onSuccess(item)
    }
    suspend fun update(
        id: String,
        nidn: String,
        nama: String,
        gelarDepan: String,
        gelarBelakang: String
    ) {
        _isLoading.postValue(true)
        dataDosenRepository.update(
            id, nidn, nama, gelarDepan, gelarBelakang,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            },
            onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            }
        )
    }

}