package com.jhbb.tracker_domain.use_case.insert_register

sealed class InsertRegisterException : Throwable() {
    object InvalidInputException : InsertRegisterException()
    object InsertionException : InsertRegisterException()
}