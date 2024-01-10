package com.jhbb.tracker_data.repository

import com.jhbb.core_data.database.dao.RegisterDao
import com.jhbb.core_data.database.entity.RegisterEntity
import com.jhbb.core_data.database.entity.toDomain
import com.jhbb.core_domain.model.CategoryType
import com.jhbb.core_domain.model.SynchronizationStatus
import com.jhbb.tracker_data.remote.RegisterService
import com.jhbb.tracker_data.remote.dto.toDto
import com.jhbb.tracker_domain.repository.RegisterRepository
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.Date

@Suppress("ClassName")
@ExperimentalCoroutinesApi
class RegisterRepositoryImplTest {

    private lateinit var subject: RegisterRepository
    private val registerDaoMock = mockk<RegisterDao>(relaxed = true)
    private val registerService = mockk<RegisterService>(relaxed = true)

    private val registersStub = listOf(
        RegisterEntity(
            title = "Titulo 1",
            description = "Descrição 1",
            value = 145.31,
            time = Date(),
            category = CategoryType.SPORTS,
            isIncome = false,
            syncStatus = SynchronizationStatus.PENDING,
        ), RegisterEntity(
            title = "Titulo 2",
            description = "Descrição 2",
            value = 451.99,
            time = Date(),
            category = CategoryType.PETS,
            isIncome = true,
            syncStatus = SynchronizationStatus.PENDING,
        )
    )

    @BeforeEach
    fun setUp() {
        subject =
            RegisterRepositoryImpl(registerDaoMock, registerService, UnconfinedTestDispatcher())
    }

    @Test
    fun `WHEN 'getAllRegisters' is called THEN it should return a list of registers`() = runTest {
        every { registerDaoMock.getAllRegisters() } returns flowOf(registersStub)

        val result = subject.getAllRegisters().first()

        coVerify(exactly = 1) { registerDaoMock.getAllRegisters() }
        assertEquals(2, result.size)
        assertEquals(registersStub.map { it.toDomain() }, result)
    }

    @Test
    fun `WHEN 'getRegistersByCategory' is called THEN it should return a list of registers`() =
        runTest {
            every { registerDaoMock.filterRegisterByCategory(listOf()) } returns flowOf(
                registersStub
            )

            val result = subject.getRegistersByCategory(listOf()).first()

            coVerify(exactly = 1) { registerDaoMock.filterRegisterByCategory(any()) }
            assertEquals(2, result.size)
            assertEquals(registersStub.map { it.toDomain() }, result)
        }

    @Test
    fun `WHEN 'insertRegister' is called THEN it should return a success result`() = runTest {
        coJustRun { registerDaoMock.insertRegister(any()) }

        val result = subject.insertRegister(registersStub[0].toDomain())

        coVerify(exactly = 1) { registerDaoMock.insertRegister(any()) }
        assertEquals(Result.success(Unit), result)
    }

    @Test
    fun `WHEN 'updateRegister' is called THEN it should return a success result`() = runTest {
        coJustRun { registerDaoMock.updateRegister(any()) }

        val result = subject.updateRegister(registersStub[0].toDomain())

        coVerify(exactly = 1) { registerDaoMock.updateRegister(any()) }
        assertEquals(Result.success(Unit), result)
    }

    @Nested
    inner class `WHEN 'synchronizeRegister' is called AND ` {
        @Test
        fun `remote service fails THEN it should return an 'RemoteSyncException'`() = runTest {
            val registerStub = registersStub[0].toDomain()
            coEvery { registerService.postRegister(registerStub.toDto()) } throws Exception()

            val result = subject.synchronizeRegister(registerStub)

            coVerify(exactly = 1) { registerService.postRegister(any()) }
            assertEquals(
                Result.failure<Throwable>(
                    RegisterRepositoryException.RemoteSyncException(registerStub)
                ), result
            )
        }

        @Test
        fun `remote service succeeds THEN it should not return an exception`() = runTest {
            val registerStub = registersStub[0].toDomain()
            coEvery {
                registerService.postRegister(registerStub.toDto())
            } returns registerStub.toDto()

            subject.synchronizeRegister(registerStub)

            coVerify(exactly = 1) { registerService.postRegister(any()) }
        }

        @Test
        fun `DAO fails THEN it should return an 'LocalSyncException'`() = runTest {
            val registerStub = registersStub[0].toDomain()
            coEvery {
                registerService.postRegister(registerStub.toDto())
            } returns registerStub.toDto()
            coEvery { registerDaoMock.updateRegister(any()) } throws Exception()

            val result = subject.synchronizeRegister(registerStub)

            coVerifyOrder {
                registerService.postRegister(any())
                registerDaoMock.updateRegister(any())
            }
            assertEquals(
                Result.failure<Throwable>(
                    RegisterRepositoryException.LocalSyncException(registerStub)
                ), result
            )
        }

        @Test
        fun `DAO succeeds THEN it should return a success result`() = runTest {
            val registerStub = registersStub[0].toDomain()
            coEvery {
                registerService.postRegister(registerStub.toDto())
            } returns registerStub.toDto()
            coEvery { registerDaoMock.updateRegister(any()) } returns Unit

            val result = subject.synchronizeRegister(registerStub)

            coVerifyOrder {
                registerService.postRegister(any())
                registerDaoMock.updateRegister(any())
            }
            assertEquals(Result.success(Unit), result)
        }
    }
}