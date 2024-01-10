package com.jhbb.tracker_domain.use_case.insert_register

import com.jhbb.tracker_domain.repository.RegisterRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
@Suppress("ClassName")
class InsertRegisterUseCaseImplTest {

    private lateinit var subject: InsertRegisterUseCase
    private val registerRepositoryMock = mockk<RegisterRepository>(relaxed = true)

    @BeforeEach
    fun setUp() {
        subject = InsertRegisterUseCaseImpl(registerRepositoryMock)
    }

    @Nested
    inner class `GIVEN that use case is invoked` {
        @Test
        fun `WHEN 'value' field passed as argument is invalid THEN it should return 'InvalidInputException'`() =
            runTest {
                val result = subject(
                    value = "value_stub",
                    title = "title_stub",
                    description = "description_stub",
                    categoryName = "category_stub",
                    isIncome = false
                )
                assertEquals(
                    Result.failure<Throwable>(InsertRegisterException.InvalidInputException),
                    result
                )
            }

        @Test
        fun `WHEN 'title' field passed as argument is blank THEN it should return 'InvalidInputException'`() =
            runTest {
                val result = subject(
                    value = "150.00",
                    title = "",
                    description = "description_stub",
                    categoryName = "EDUCATION",
                    isIncome = false
                )
                assertEquals(
                    Result.failure<Throwable>(InsertRegisterException.InvalidInputException),
                    result
                )
            }

        @Test
        fun `WHEN 'description' field passed as argument is blank THEN it should return 'InvalidInputException'`() =
            runTest {
                val result = subject(
                    value = "150.00",
                    title = "title_stub",
                    description = "",
                    categoryName = "EDUCATION",
                    isIncome = false
                )
                assertEquals(
                    Result.failure<Throwable>(InsertRegisterException.InvalidInputException),
                    result
                )
            }

        @Test
        fun `WHEN 'categoryName' field passed as argument is blank THEN it should return 'InvalidInputException'`() =
            runTest {
                val result = subject(
                    value = "150.00",
                    title = "title_stub",
                    description = "description_stub",
                    categoryName = "",
                    isIncome = false
                )
                assertEquals(
                    Result.failure<Throwable>(InsertRegisterException.InvalidInputException),
                    result
                )
            }

        @Test
        fun `WHEN repository invocation succeeds THEN it should return success`() =
            runTest {
                coEvery { registerRepositoryMock.insertRegister(any()) } returns Result.success(Unit)
                val result = subject(
                    value = "150.00",
                    title = "title_stub",
                    description = "description_stub",
                    categoryName = "EDUCATION",
                    isIncome = false
                )
                assertEquals(Result.success(Unit), result)
            }

        @Test
        fun `WHEN repository invocation fails THEN it should return a failure`() =
            runTest {
                coEvery {
                    registerRepositoryMock.insertRegister(any())
                } returns Result.failure(Throwable())
                val result = subject(
                    value = "150.00",
                    title = "title_stub",
                    description = "description_stub",
                    categoryName = "EDUCATION",
                    isIncome = false
                )
                assertEquals(
                    Result.failure<InsertRegisterException>(InsertRegisterException.InsertionException),
                    result
                )
            }
    }
}