package com.frog.androidassestment

import com.frog.androidassestment.testUtil.emailAddressValidationTestData
import com.movieapp.core.data.validator.impl.EmailValidator
import com.movieapp.core.domain.ValidateEmailUseCase
import com.movieapp.core.util.TaskResult
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(value = Parameterized::class)
class ValidateEmailUserCaseTest(private val input: String, private val expectedValue: Boolean) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters()
        fun data(): List<Array<Any>> {
            return emailAddressValidationTestData
        }
    }

    private lateinit var validateEmailUseCase: ValidateEmailUseCase

    @Before
    fun init(){
        validateEmailUseCase = ValidateEmailUseCase(EmailValidator())
    }

    @Test
    fun validateEmail(){
        val validationResult = when(validateEmailUseCase(input)){

            is TaskResult.Success -> true
            else -> {false}
        }
        assertEquals(expectedValue,validationResult)
    }
}