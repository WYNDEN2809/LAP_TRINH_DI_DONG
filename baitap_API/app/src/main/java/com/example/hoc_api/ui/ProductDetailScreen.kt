package com.example.hoc_api.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.hoc_api.model.Product
import com.example.hoc_api.viewmodel.ProductUiState
import com.example.hoc_api.viewmodel.ProductViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    viewModel: ProductViewModel = viewModel(),
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Product detail",
                        color = Color(0xFF2196F3),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF29B6F6))
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val state = uiState) {
                is ProductUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color(0xFF2196F3)
                    )
                }

                is ProductUiState.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Lỗi: ${state.message}",
                            color = Color.Red,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.fetchProduct() }) {
                            Text(text = "Thử lại")
                        }
                    }
                }

                is ProductUiState.Success -> {
                    ProductDetailContent(product = state.product)
                }
            }
        }
    }
}

@Composable
fun ProductDetailContent(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        // Ảnh sản phẩm lớn, bo góc, nền cam/đỏ
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFFF5722)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = product.imgURL,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tên sản phẩm (đậm)
        Text(
            text = product.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF212121),
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Giá (màu đỏ, định dạng tiền Việt Nam)
        Text(
            text = "Giá: ${formatVnCurrency(product.price)}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD32F2F)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Mô tả sản phẩm (text dài trong khung nền xám nhạt bo góc giống ảnh mẫu)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {
            Text(
                text = product.des,
                fontSize = 14.sp,
                color = Color(0xFF424242),
                lineHeight = 20.sp,
                textAlign = TextAlign.Justify
            )
        }
    }
}

/**
 * Định dạng số thành tiền Việt Nam (ví dụ: 4.000.000đ)
 */
fun formatVnCurrency(price: Double): String {
    val formatter = NumberFormat.getNumberInstance(Locale("vi", "VN")).apply {
        maximumFractionDigits = if (price % 1.0 == 0.0) 0 else 2
    }
    return "${formatter.format(price)}đ"
}

@Preview(showBackground = true)
@Composable
fun ProductDetailPreview() {
    ProductDetailContent(
        product = Product(
            id = "1",
            name = "Giày Nike Nam Nữ Chính Hãng - Nike Air Force 1 '07 LV8 - Màu Trắng | JapanSport HF2898-100",
            price = 4000000.0,
            des = "Với giày chạy bộ, từng gram đều quan trọng. Đó là lý do tại sao đế giữa LIGHTSTRIKE PRO mới nhẹ hơn so với phiên bản trước. Mút foam đế giữa siêu nhẹ và thoải mái này có lớp đệm đàn hồi được thiết kế để hạn chế tiêu hao năng lượng.",
            imgURL = "https://avatars.githubusercontent.com/u/6291597"
        )
    )
}
