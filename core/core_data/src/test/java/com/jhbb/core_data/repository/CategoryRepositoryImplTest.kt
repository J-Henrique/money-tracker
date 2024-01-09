package com.jhbb.core_data.repository

import com.jhbb.core_data.database.dao.CategoryDao
import com.jhbb.core_data.database.entity.CategoryEntity
import com.jhbb.core_data.database.entity.toDomain
import com.jhbb.core_domain.repository.CategoryRepository
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@ExperimentalCoroutinesApi
class CategoryRepositoryImplTest {

    private lateinit var subject: CategoryRepository
    private val categoryDaoMock = mockk<CategoryDao>(relaxed = true)

    @BeforeEach
    fun setUp() {
        subject = CategoryRepositoryImpl(categoryDaoMock)
    }

    @Nested
    inner class `WHEN 'getCategories' is called passing` {
        @Test
        fun `null as argument THEN it should get all categories`() = runTest {
            val categoriesStub = listOf(
                CategoryEntity(1, "PETS", isEnabled = true),
                CategoryEntity(2, "INVESTMENTS", isEnabled = false),
            )
            coEvery { categoryDaoMock.selectCategory() } returns categoriesStub
            val result = subject.getCategories(null)
            coVerify(exactly = 1) { categoryDaoMock.selectCategory() }
            assertEquals(categoriesStub.map { it.toDomain() }, result)
        }

        @Test
        fun `true as argument THEN it should get categories by filter`() = runTest {
            val categoriesStub = listOf(
                CategoryEntity(1, "PETS", isEnabled = true),
                CategoryEntity(2, "INVESTMENTS", isEnabled = false),
            )
            coEvery { categoryDaoMock.selectCategoryByFilter(any()) } returns categoriesStub
            val result = subject.getCategories(true)
            coVerify(exactly = 1) { categoryDaoMock.selectCategoryByFilter(true) }
            assertEquals(categoriesStub.map { it.toDomain() }, result)
        }

        @Test
        fun `false as argument THEN it should get categories by filter`() = runTest {
            val categoriesStub = listOf(
                CategoryEntity(1, "PETS", isEnabled = true),
                CategoryEntity(2, "INVESTMENTS", isEnabled = false),
            )
            coEvery { categoryDaoMock.selectCategoryByFilter(any()) } returns categoriesStub
            val result = subject.getCategories(false)
            coVerify(exactly = 1) { categoryDaoMock.selectCategoryByFilter(false) }
            assertEquals(categoriesStub.map { it.toDomain() }, result)
        }
    }

    @Nested
    inner class `WHEN 'updateCategories' is called AND DAO function` {
        @Test
        fun `succeeds THEN it should return a success result`() = runTest {
            coJustRun { categoryDaoMock.updateCategories(any()) }
            val result = subject.updateCategories(listOf())
            assertEquals(Result.success(Unit), result)
        }

        @Test
        fun `fails THEN it should return a failure result`() = runTest {
            val exceptionStub = Exception()
            coEvery { categoryDaoMock.updateCategories(any()) } throws exceptionStub
            val result = subject.updateCategories(listOf())
            assertEquals(Result.failure<Exception>(exceptionStub), result)
        }
    }
}