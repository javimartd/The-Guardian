package com.javimartd.theguardian.ui.news.state

class Resource<out T> constructor(val status: Status,
                                  val data: T?,
                                  val message: String?)