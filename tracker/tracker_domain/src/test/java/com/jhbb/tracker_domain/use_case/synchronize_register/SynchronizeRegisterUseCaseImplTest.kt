package com.jhbb.tracker_domain.use_case.synchronize_register

import app.cash.turbine.test
import com.jhbb.core_domain.model.CategoryType
import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.model.SynchronizationStatus
import com.jhbb.tracker_domain.repository.RegisterRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Date

@ExperimentalCoroutinesApi
class SynchronizeRegisterUseCaseImplTest {

    private lateinit var subject: SynchronizeRegisterUseCase
    private val registerRepository = mockk<RegisterRepository>()

    @BeforeEach
    fun setUp() {
        subject = SynchronizeRegisterUseCaseImpl(registerRepository)
    }

    @Test
    fun `GIVEN that use case is invoked WHEN repository invocation is called for each item THEN it should return the respective value`() =
        runTest {
            val successStub = Register(
                title = "Titulo 1",
                description = "Descrição 1",
                value = 145.31,
                time = Date(),
                categoryType = CategoryType.SPORTS,
                isIncome = false,
                syncStatus = SynchronizationStatus.PENDING,
            )
            val failingStub = Register(
                title = "Titulo 2",
                description = "Descrição 2",
                value = 891.92,
                time = Date(),
                categoryType = CategoryType.TRANSPORT,
                isIncome = false,
                syncStatus = SynchronizationStatus.PENDING,
            )

            coEvery {
                registerRepository.synchronizeRegister(successStub)
            } returns Result.success(Unit)
            coEvery {
                registerRepository.synchronizeRegister(failingStub)
            } returns Result.failure(Throwable())

            subject(
                listOf(successStub, failingStub, successStub)
            ).test {
                assertEquals(Result.success(successStub), awaitItem())
                assertInstanceOf(
                    SynchronizationException::class.java,
                    awaitItem().exceptionOrNull()
                )
                assertEquals(Result.success(successStub), awaitItem())
                awaitComplete()
            }
        }
}