package com.hafhashtad.android

import androidx.lifecycle.viewModelScope
import com.hafthashtad.android.core.data.model.previewProducts
import com.hafthashtad.android.core.domain.hafthashtad.GetProductsUseCase
import com.hafthashtad.android.feature.home.HomeContract
import com.hafthashtad.android.feature.home.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    val productsUseCase: GetProductsUseCase = mockk(relaxed = true)

    private lateinit var viewModel: HomeViewModel

    private val input = listOf(previewProducts)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init()
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)
        viewModel = HomeViewModel(
            getProductsUseCase = productsUseCase
        )
    }

    @Test
    fun `check first ui state`() {
        runTest {
            Assert.assertEquals(
                HomeContract.UiState.Loading,
                viewModel.productList.stateIn(viewModel.viewModelScope).value
            )
        }
    }

    @Test
    fun `ui state after success service Call returns list of product`() {
        runTest {
            every {
                productsUseCase()
            } answers {
                flowOf(input)
            }
            viewModel.fetchProducts()
            assert(viewModel.productList.stateIn(viewModel.viewModelScope).value is HomeContract.UiState.Success)
        }
    }

    @Test
    fun `ui state after exception on domain is Error`() {
        runTest {
            every {
                productsUseCase()
            } answers {
                flow {
                    throw Exception()
                }
            }
            viewModel.fetchProducts()
            assert(viewModel.productList.stateIn(viewModel.viewModelScope).value is HomeContract.UiState.Failure)
        }
    }

    @Test
    fun `filtered Data only contains query`() {
        runTest {
            every {
                productsUseCase()
            } answers {
                flowOf(input)
            }
            viewModel.fetchProducts()
            viewModel.onQueryChanged("h")
            assert(
                (viewModel.productList.stateIn(viewModel.viewModelScope).value as
                        HomeContract.UiState.Success).data.isEmpty()
            )
        }
    }

    @Test
    fun `filtered Data holds contains query`() {
        runTest {
            every {
                productsUseCase()
            } answers {
                flowOf(input)
            }
            viewModel.fetchProducts()
            viewModel.onQueryChanged("Fjallraven")
            assert(
                (viewModel.productList.stateIn(viewModel.viewModelScope).value as
                        HomeContract.UiState.Success).data.isNotEmpty()
            )
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        clearAllMocks()
        unmockkAll()
        Dispatchers.resetMain()
    }

}