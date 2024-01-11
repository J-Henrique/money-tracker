package com.jhbb.tracker_presentation.ui.home

import InstantExecutorExtension
import com.jhbb.core_domain.model.Category
import com.jhbb.core_domain.model.CategoryType
import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.model.SynchronizationStatus
import com.jhbb.core_domain.repository.CategoryRepository
import com.jhbb.core_ui.ui.components.category_card.toUiModel
import com.jhbb.tracker_domain.repository.RegisterRepository
import com.jhbb.tracker_domain.use_case.synchronize_register.SynchronizationException
import com.jhbb.tracker_domain.use_case.synchronize_register.SynchronizeRegisterUseCase
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Date

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
@Suppress("ClassName")
class HomeScreenViewModelTest {

    private lateinit var homeScreenViewModel: HomeScreenViewModel
    private val registerRepositoryMock = mockk<RegisterRepository>(relaxed = true)
    private val categoryRepositoryMock = mockk<CategoryRepository>(relaxed = true)
    private val synchronizeRegisterUseCaseMock = mockk<SynchronizeRegisterUseCase>(relaxed = true)

    private val registersStub = listOf(
        Register(
            id = 1,
            title = "Titulo 1",
            description = "Descrição 1",
            value = 145.31,
            time = Date(),
            categoryType = CategoryType.EDUCATION,
            isIncome = true,
            syncStatus = SynchronizationStatus.PENDING
        ),
        Register(
            id = 2,
            title = "Titulo 2",
            description = "Descrição 2",
            value = 112.98,
            time = Date(),
            categoryType = CategoryType.PETS,
            isIncome = false,
            syncStatus = SynchronizationStatus.PENDING
        ),
        Register(
            id = 3,
            title = "Titulo 3",
            description = "Descrição 3",
            value = 978.29,
            time = Date(),
            categoryType = CategoryType.SPORTS,
            isIncome = false,
            syncStatus = SynchronizationStatus.PENDING
        )
    )

    private val categoriesStub = listOf(
        Category(1, CategoryType.BAR, true),
        Category(2, CategoryType.FOOD, false),
        Category(3, CategoryType.EDUCATION, false),
        Category(4, CategoryType.PETS, true)
    )

    @BeforeEach
    fun setUp() {
        every { registerRepositoryMock.getAllRegisters() } returns flowOf(registersStub)

        every { synchronizeRegisterUseCaseMock(any()) } returns flowOf(
            Result.success(registersStub[0]),
            Result.success(registersStub[1]),
            Result.success(registersStub[2])
        )

        coEvery { categoryRepositoryMock.getCategories(true) } returns categoriesStub
    }

    @Nested
    inner class `WHEN viewModel is initialized` {
        @Test
        fun `THEN it should set 3 registers on state`() = runTest {
            homeScreenViewModel = HomeScreenViewModel(
                registerRepository = registerRepositoryMock,
                categoryRepository = categoryRepositoryMock,
                synchronizeRegisterUseCase = synchronizeRegisterUseCaseMock
            )

            verify(exactly = 1) { registerRepositoryMock.getAllRegisters() }
            assertEquals(3, homeScreenViewModel.state.registers.size)
        }

        @Test
        fun `THEN it should synchronize every pending register on state`() = runTest {
            homeScreenViewModel = HomeScreenViewModel(
                registerRepository = registerRepositoryMock,
                categoryRepository = categoryRepositoryMock,
                synchronizeRegisterUseCase = synchronizeRegisterUseCaseMock
            )

            verify(exactly = 1) { synchronizeRegisterUseCaseMock(any()) }
            homeScreenViewModel.state.registers.forEach {
                assertEquals(SynchronizationStatus.SUCCESS, it.syncStatus)
            }
        }

        @Test
        fun `AND synchronization fails THEN it should set failed status on every register on state`() =
            runTest {
                every { synchronizeRegisterUseCaseMock(any()) } returns flowOf(
                    Result.failure(SynchronizationException(registersStub[0])),
                    Result.failure(SynchronizationException(registersStub[1])),
                    Result.failure(SynchronizationException(registersStub[2])),
                )

                homeScreenViewModel = HomeScreenViewModel(
                    registerRepository = registerRepositoryMock,
                    categoryRepository = categoryRepositoryMock,
                    synchronizeRegisterUseCase = synchronizeRegisterUseCaseMock
                )

                verify(exactly = 1) { synchronizeRegisterUseCaseMock(any()) }
                homeScreenViewModel.state.registers.forEach {
                    assertEquals(SynchronizationStatus.ERROR, it.syncStatus)
                }
            }

        @Test
        fun `THEN it should set 4 categories on 'categoriesFilter' state`() = runTest {
            homeScreenViewModel = HomeScreenViewModel(
                registerRepository = registerRepositoryMock,
                categoryRepository = categoryRepositoryMock,
                synchronizeRegisterUseCase = synchronizeRegisterUseCaseMock
            )

            coVerify(exactly = 1) { categoryRepositoryMock.getCategories(true) }
            assertEquals(4, homeScreenViewModel.categoriesFilter.size)
        }

        @Test
        fun `THEN it all categories to UI model AND set these objects with 'isEnabled' equals to false on 'categoriesFilter' state`() =
            runTest {
                homeScreenViewModel = HomeScreenViewModel(
                    registerRepository = registerRepositoryMock,
                    categoryRepository = categoryRepositoryMock,
                    synchronizeRegisterUseCase = synchronizeRegisterUseCaseMock
                )

                coVerify(exactly = 1) { categoryRepositoryMock.getCategories(true) }
                homeScreenViewModel.categoriesFilter.forEach {
                    assertFalse(it.isEnabled)
                }
            }
    }

    @Test
    fun `WHEN refresh is called for a item THEN it synchronize this item`() = runTest {
        homeScreenViewModel = HomeScreenViewModel(
            registerRepository = registerRepositoryMock,
            categoryRepository = categoryRepositoryMock,
            synchronizeRegisterUseCase = synchronizeRegisterUseCaseMock
        )

        clearMocks(synchronizeRegisterUseCaseMock) // bypass previous calls at viewModel initialization
        homeScreenViewModel.refreshItem(registersStub[0])

        verify(exactly = 1) { synchronizeRegisterUseCaseMock.invoke(any()) }
    }

    @Nested
    inner class `GIVEN that 'onEvent' is called with` {
        @BeforeEach
        fun setUp() {
            homeScreenViewModel = HomeScreenViewModel(
                registerRepository = registerRepositoryMock,
                categoryRepository = categoryRepositoryMock,
                synchronizeRegisterUseCase = synchronizeRegisterUseCaseMock
            )
        }

        @Test
        fun `'OnFilter' THEN it should set 3 registers on state`() = runTest {
            every {
                registerRepositoryMock.getRegistersByCategory(any())
            } returns flowOf(registersStub)

            homeScreenViewModel.onEvent(HomeScreenEvent.OnFilter)

            verify(exactly = 1) { registerRepositoryMock.getRegistersByCategory(any()) }
            assertEquals(3, homeScreenViewModel.state.registers.size)
        }

        @Test
        fun `'OnClearFilter' THEN it should set false to all categories on state AND invoke repository`() =
            runTest {
                clearMocks(registerRepositoryMock)
                homeScreenViewModel.onEvent(HomeScreenEvent.OnClearFilter)

                verify(exactly = 1) { registerRepositoryMock.getAllRegisters() }
                homeScreenViewModel.categoriesFilter.forEach {
                    assertFalse(it.isEnabled)
                }
            }

        @Test
        fun `'OnSelectFilter' THEN it should look for this category AND invert its status`() =
            runTest {
                homeScreenViewModel.onEvent(
                    HomeScreenEvent.OnSelectFilter(categoriesStub[2].toUiModel())
                )

                assertTrue(homeScreenViewModel.categoriesFilter[2].isEnabled)
            }
    }
}