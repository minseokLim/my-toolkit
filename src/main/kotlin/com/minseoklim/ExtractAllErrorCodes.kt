package com.minseoklim

import com.minseoklim.util.extractAllIncludedFiles
import java.io.File

private val badRequestExceptionRegex = "BadRequestException\\(\"(.*)\", \"(.*)\"\\)".toRegex()
private val notFoundExceptionRegex = "NotFoundException\\(\"(.*)\", \"(.*)\"\\)".toRegex()
private val noPermissionExceptionRegex = "NoPermissionException\\(\"(.*)\", \"(.*)\"\\)".toRegex()

fun main() {
    val targetDirectory = File("/Users/minseoklim/workspace/study/practice/toy-project-2024/backend")
    val targetFiles = targetDirectory.extractAllIncludedFiles()
    extractAllErrorCodes(targetFiles).forEach { println("$it,") }
}

private fun extractAllErrorCodes(files: List<File>): List<Error> {
    return files.asSequence().flatMap { it.readLines() }
        .flatMap { line ->
            listOf(
                badRequestExceptionRegex.find(line),
                notFoundExceptionRegex.find(line),
                noPermissionExceptionRegex.find(line)
            )
        }
        .filterNotNull()
        .map { Error(it.groupValues[1], it.groupValues[2])  }
        .distinct()
        .toList()
}

class Error(
    val code: String,
    val message: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Error

        return code == other.code
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }

    override fun toString(): String {
        return "$code(\"$message\")"
    }
}
