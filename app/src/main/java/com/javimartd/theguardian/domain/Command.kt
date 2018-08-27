package com.javimartd.theguardian.domain

interface Command <T>{
    fun execute(): T
}