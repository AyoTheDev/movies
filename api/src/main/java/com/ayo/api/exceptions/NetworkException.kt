package com.ayo.api.exceptions

import java.io.IOException

open class NetworkException(val code : Int, message : String) : IOException(message)