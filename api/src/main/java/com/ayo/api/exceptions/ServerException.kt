package com.ayo.api.exceptions

class ServerException(code: Int, message: String) : NetworkException(code, message)