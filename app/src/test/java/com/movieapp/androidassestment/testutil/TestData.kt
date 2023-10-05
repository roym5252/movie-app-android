package com.movieapp.androidassestment.testutil

data class TestLoginCredential(val email: String, val password: String)

val passwordValidationTestData: List<Array<Any>> = listOf(
    arrayOf("", false),
    arrayOf("1234567890", true),
    arrayOf("test_password", true),
    arrayOf("test_password_11122", true),
    arrayOf("test_password%$^*UI#@#!", true),
    arrayOf("%$^*UI#@#!", true),
)


val loginTestData: List<Array<Any>> = listOf(
    arrayOf(TestLoginCredential("", ""), false),
    arrayOf(TestLoginCredential("test@testmail.com", ""), false),
    arrayOf(TestLoginCredential("", "password"), false),
    arrayOf(TestLoginCredential("test@testmail.com", "password"), true)
)

const val validMovieJsonResponse =
    "{\"Search\":[{\"Title\":\"The Tangerine Bear: Home in Time for Christmas!\",\"Year\":\"2000\",\"imdbID\":\"tt0268677\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMzU5Nzc2MTM0MF5BMl5BanBnXkFtZTYwMzY3Njk4._V1_SX300.jpg\"},{\"Title\":\"David Blaine: Frozen in Time\",\"Year\":\"2000\",\"imdbID\":\"tt0269133\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNGY0Zjc2YzgtNDM0Zi00ZjYxLWE1M2QtMTA4MjVkZWNmN2I3XkEyXkFqcGdeQXVyNDkwMzY5NjQ@._V1_SX300.jpg\"},{\"Title\":\"The Sands of Time\",\"Year\":\"2000\",\"imdbID\":\"tt0211396\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTYzNTA2NDUwNV5BMl5BanBnXkFtZTcwMTk5ODI0MQ@@._V1_SX300.jpg\"},{\"Title\":\"Out of Time\",\"Year\":\"2000\",\"imdbID\":\"tt0250635\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMjE4NjQ0NzU4NV5BMl5BanBnXkFtZTcwMzU5NzkxMQ@@._V1_SX300.jpg\"},{\"Title\":\"Tina Turner: One Last Time Live in Concert\",\"Year\":\"2000\",\"imdbID\":\"tt0286227\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTI2NTI1NzU5OF5BMl5BanBnXkFtZTcwMjMwNTEzMQ@@._V1_SX300.jpg\"},{\"Title\":\"Songs in Ordinary Time\",\"Year\":\"2000\",\"imdbID\":\"tt0227526\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMjE5ODI5NDk4OF5BMl5BanBnXkFtZTcwOTM3MjgyMg@@._V1_SX300.jpg\"},{\"Title\":\"Birthday Time\",\"Year\":\"2000\",\"imdbID\":\"tt0303769\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Child of Our Time\",\"Year\":\"2000–\",\"imdbID\":\"tt0290348\",\"Type\":\"series\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMDg1ZjhjZDEtYjgyOS00NDQxLThiMGItMTJlZDdmNjUxNjIwXkEyXkFqcGdeQXVyMTA0NTY0NTYy._V1_SX300.jpg\"},{\"Title\":\"The Exotic Time Machine II: Forbidden Encounters\",\"Year\":\"2000\",\"imdbID\":\"tt0259300\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BYTY3OGNhZmItM2Q4NS00YjkzLWFmN2QtMWZkMTBkMTAyNjg3XkEyXkFqcGdeQXVyMTE5MzU1Njkw._V1_SX300.jpg\"},{\"Title\":\"Daft Punk: One More Time\",\"Year\":\"2000\",\"imdbID\":\"tt7431810\",\"Type\":\"movie\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BM2VhMjljYzEtNjJiZi00NTk1LWExZDctOWFmMTI3MzU0MzI0XkEyXkFqcGdeQXVyNzA1OTk3Mw@@._V1_SX300.jpg\"}],\"totalResults\":\"81\",\"Response\":\"True\"}\n"

const val testApiKey = "test_api_key"
const val testTitle = "test_title"
const val testPage = 1
const val testYear = 2000