package com.movieapp.core.ui

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : BaseActivity()  {

    suspend fun getResults(from:Int,to:Int):Set<Long>{

        val results = HashSet<Long>()

        for (i in from until to){

            val resultFromExpensiveFunction = try {
                expensiveAndRiskyFunction(i)
            }catch (e:ArithmeticException){
                null
            }

            resultFromExpensiveFunction?.let {
                results.add(it)
            }
        }

        return results
    }

    private fun expensiveAndRiskyFunction(i:Int):Long{
        return 5L
    }
}