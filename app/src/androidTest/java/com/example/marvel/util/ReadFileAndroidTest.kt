package com.example.marvel.util

class ReadFileAndroidTest {
    operator fun invoke(path: String) = this.javaClass.classLoader?.getResource(path)?.readText()
}