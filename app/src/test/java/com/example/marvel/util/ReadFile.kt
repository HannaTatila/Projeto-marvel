package com.example.marvel.util

class ReadFile {
    operator fun invoke(path: String) = this.javaClass.classLoader?.getResource(path)?.readText()
}