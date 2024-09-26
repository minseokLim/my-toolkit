package com.minseoklim

import com.minseoklim.util.extractAllIncludedFiles
import com.minseoklim.util.replaceTexts
import java.io.File

fun main() {
    val targetDirectory =
        File("/Users/minseoklim/workspace/study/practice/toy-project-2024/frontend/src/main/resources/templates")
    val targetFiles = targetDirectory.extractAllIncludedFiles()
    targetFiles.forEach { it.replaceTexts(getReplaceMap()) }
}

private fun getReplaceMap(): Map<String, String> {
    return File("src/main/resources/messages").readLines()
        .map { it.split("=") }
        .associate { it[0] to it[1] }
}
