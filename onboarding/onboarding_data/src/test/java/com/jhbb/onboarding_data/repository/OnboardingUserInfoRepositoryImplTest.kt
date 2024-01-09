package com.jhbb.onboarding_data.repository

import com.jhbb.core_domain.model.Category
import com.jhbb.core_domain.model.CategoryType
import com.jhbb.core_domain.preferences.Preferences
import com.jhbb.core_domain.repository.CategoryRepository
import com.jhbb.onboarding_domain.repository.OnboardingUserInfoRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class OnboardingUserInfoRepositoryImplTest {

    private lateinit var subject: OnboardingUserInfoRepository
    private val preferencesMock = mockk<Preferences>(relaxed = true)
    private val categoriesRepositoryMock = mockk<CategoryRepository>(relaxed = true)

    @BeforeEach
    fun setUp() {
        subject = OnboardingUserInfoRepositoryImpl(preferencesMock, categoriesRepositoryMock)
    }

    @Test
    fun `WHEN 'saveUserName' is called THEN it should persist the argument passed`() {
        justRun { preferencesMock.putUserName(any()) }
        subject.saveUserName("test_user_name")
        verify(exactly = 1) { preferencesMock.putUserName("test_user_name") }
    }

    @Test
    fun `WHEN 'getUserName' is called THEN it should return the value that was persisted`() =
        runTest {
            every { preferencesMock.getUserName() } returns "test_user_name"
            val result = subject.getUserName()
            verify(exactly = 1) { preferencesMock.getUserName() }
            assertEquals("test_user_name", result)
        }

    @Test
    fun `WHEN 'getCategories' is called THEN it should return a list of categories`() =
        runTest {
            val categoriesStub = listOf(
                Category(1, CategoryType.PETS, isEnabled = true),
                Category(2, CategoryType.INVESTMENTS, isEnabled = false),
                Category(3, CategoryType.BAR, isEnabled = false)
            )
            coEvery { categoriesRepositoryMock.getCategories() } returns categoriesStub
            val result = subject.getCategories()
            assertEquals(3, result.size)
            assertEquals(categoriesStub, result)
        }

    @Test
    fun `WHEN 'updateCategories' is called THEN it should call repository AND return a result`() =
        runTest {
            val categoriesStub = listOf(
                Category(1, CategoryType.PETS, isEnabled = true),
                Category(2, CategoryType.INVESTMENTS, isEnabled = false),
                Category(3, CategoryType.BAR, isEnabled = false)
            )
            coEvery {
                categoriesRepositoryMock.updateCategories(categoriesStub)
            } returns Result.success(Unit)
            val result = subject.updateCategories(categoriesStub)
            assertEquals(Result.success(Unit), result)
        }
}