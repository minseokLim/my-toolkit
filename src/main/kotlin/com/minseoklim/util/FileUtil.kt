package com.minseoklim.util

import java.io.File

fun File.extractAllIncludedFiles(): List<File> {
    return if (this.isDirectory) {
        this.listFiles()!!.flatMap {
            it.extractAllIncludedFiles()
        }
    } else {
        listOf(this)
    }
}

fun File.extractAllIncludedDirectories(): List<File> {
    return if (this.isDirectory) {
        this.listFiles()!!.flatMap {
            it.extractAllIncludedDirectories()
        } + this
    } else {
        emptyList()
    }
}
