package com.movieapp.androidassestment.test.domain

import com.movieapp.androidassestment.testutil.passwordValidationTestData
import com.movieapp.core.data.validator.impl.PasswordValidator
import com.movieapp.core.domain.ValidatePasswordUseCase
import com.movieapp.core.util.TaskResult
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(value = Parameterized::class)
class ValidatePasswordUserCaseTest(private val input: String, private val expectedValue: Boolean) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters()
        fun data(): List<Array<Any>> {
            return passwordValidationTestData
        }
    }

    private lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @Before
    fun init(){
        validatePasswordUseCase = ValidatePasswordUseCase(PasswordValidator())
    }

    @Test
    fun validatePassword(){
        val result = validatePasswordUseCase(input)
        assertEquals(expectedValue,result is TaskResult.Success<*>)
    }
}