package com.example.hoc_api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoc_api.model.Product
import com.example.hoc_api.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed interface ProductUiState {
    data object Loading : ProductUiState
    data class Success(val product: Product) : ProductUiState
    data class Error(val message: String) : ProductUiState
}

class ProductViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        fetchProduct()
    }

    fun fetchProduct() {
        viewModelScope.launch {
            _uiState.value = ProductUiState.Loading
            try {
                val product = RetrofitInstance.api.getProduct()
                _uiState.value = ProductUiState.Success(product)
            } catch (e: SocketTimeoutException) {
                _uiState.value = ProductUiState.Error(
                    "Quá thời gian kết nối máy chủ (Socket Timeout). Vui lòng thử lại!"
                )
            } catch (e: UnknownHostException) {
                _uiState.value = ProductUiState.Error(
                    "Không có kết nối Internet hoặc không tìm thấy máy chủ."
                )
            } catch (e: IOException) {
                _uiState.value = ProductUiState.Error(
                    "Lỗi mạng: ${e.localizedMessage ?: "Không thể kết nối"}"
                )
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error(
                    e.localizedMessage ?: "Đã xảy ra lỗi khi tải dữ liệu"
                )
            }
        }
    }
}
