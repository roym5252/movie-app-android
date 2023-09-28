package com.movieapp.androidassestment.testdata

data class TestLoginCredential(val email:String,val password:String)


val passwordValidationTestData:List<Array<Any>> = listOf(
    arrayOf("", false),
    arrayOf("1234567890", true),
    arrayOf("test_password", true),
    arrayOf("test_password_11122", true),
    arrayOf("test_password%$^*UI#@#!", true),
    arrayOf("%$^*UI#@#!", true),
)


val loginTestData:List<Array<Any>> = listOf(
    arrayOf(TestLoginCredential("",""),false),
    arrayOf(TestLoginCredential("test@testmail.com",""),false),
    arrayOf(TestLoginCredential("","password"),false),
    arrayOf(TestLoginCredential("test@testmail.com","password"),true)
)

