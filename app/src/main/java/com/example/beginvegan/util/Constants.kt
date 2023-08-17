package com.example.beginvegan.util

object Constants {
    const val BASE_URL = "http://beginvegan.p-e.kr/"


    // VALIDATION
    const val EMAIL_VALIDATION =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    const val NICKNAME_VALIDATION = "^[가-힣]{1,8}$"
    const val PW_VALIDATION = """^[0-9a-zA-Z!@#$%^+\-=]*$"""

    // Permission const code
    const val ACCESS_FINE_LOCATION = 1000
}