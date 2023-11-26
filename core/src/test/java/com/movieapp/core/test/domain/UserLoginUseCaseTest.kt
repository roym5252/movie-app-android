package com.movieapp.core.test.domain

import android.app.Application
import com.movieapp.core.data.datasource.local.LocalDataSourceManagerImpl
import com.movieapp.core.data.repository.impl.UserRepositoryImpl
import com.movieapp.core.data.validator.impl.PasswordValidator
import com.movieapp.core.domain.UserLoginUseCase
import com.movieapp.core.domain.ValidateEmailUseCase
import com.movieapp.core.domain.ValidatePasswordUseCase
import com.movieapp.core.testutil.MainCoroutineRule
import com.movieapp.core.testutil.TestLoginCredential
import com.movieapp.core.testutil.loginTestData
import com.movieapp.core.util.CommonUtil
import com.movieapp.core.util.PrefUtil
import com.movieapp.core.util.TaskResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.MockitoAnnotations


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(value = Parameterized::class)
class UserLoginUseCaseTest(private val input: TestLoginCredential, private val expectedValue: Boolean) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters()
        fun data(): List<Array<Any>> {
            return loginTestData
        }
    }

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var commonUtil: CommonUtil

    @Mock
    private lateinit var validateEmailUseCase: ValidateEmailUseCase

    @Mock
    private lateinit var dataSourceManager: LocalDataSourceManagerImpl

    @Mock
    private lateinit var prefUtil: PrefUtil


    private lateinit var userRepository: UserRepositoryImpl
    private lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @Before
    fun setUp() {

        MockitoAnnotations.openMocks(this)

        userRepository = UserRepositoryImpl(dataSourceManager, prefUtil)
        validatePasswordUseCase = ValidatePasswordUseCase(PasswordValidator())

        Mockito.`when`(commonUtil.checkNetworkConnectivity(application)).thenReturn(TaskResult.Success(true))
        doNothing().`when`(prefUtil).saveBoolean(anyString(), Mockito.anyBoolean())
    }

    @Test
    fun loginTest() = runTest{

        if(input.email.isNotEmpty()){

            Mockito.`when`(validateEmailUseCase.invoke(input.email)).thenReturn(TaskResult.Success(true))
            Mockito.`when`(dataSourceManager.checkAccountExists(input.email)).thenReturn(true)

            if(input.password.isNotEmpty()){
                Mockito.`when`(dataSourceManager.authenticate(input.email,input.password)).thenReturn(true)
            }
        }

        val result = UserLoginUseCase(userRepository)(input.email,input.password)
        assertEquals(expectedValue,result is TaskResult.Success<*>)

    }
}