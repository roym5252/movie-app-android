package com.movieapp.core.util

import androidx.security.crypto.MasterKeys

interface IMasterKeyBuilderWrapper {
    fun build(): MasterKeys
}