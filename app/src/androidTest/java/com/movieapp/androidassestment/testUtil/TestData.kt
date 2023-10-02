package com.movieapp.androidassestment.testUtil

val emailAddressValidationTestData:List<Array<Any>> = listOf(
    arrayOf("john@test.com", true),
    arrayOf("john@test.co.in", true),
    arrayOf("john_2001@test.com", true),
    arrayOf("", false),
    arrayOf("john@test", false),
    arrayOf("@test.com", false),
    arrayOf("@test", false),
    arrayOf("test.com", false),
    arrayOf("john@", false),
    arrayOf("john", false),
    arrayOf(".com", false)
)