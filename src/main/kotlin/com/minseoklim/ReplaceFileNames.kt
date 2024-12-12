package com.minseoklim

import com.minseoklim.util.extractAllIncludedDirectories
import com.minseoklim.util.extractAllIncludedFiles
import java.io.File

fun main() {
    val targetDirectory = File("/Users/minseoklim/workspace/ratel/c8c-api")
    val targetFiles = targetDirectory.extractAllIncludedFiles()
    targetFiles.forEach { replaceFileName(it, "Feed", "Portfolio") }

    val targetDirectories = targetDirectory.extractAllIncludedDirectories()
    targetDirectories.forEach { replaceFileName(it, "feed", "portfolio") }
}

private fun replaceFileName(file: File, before: String, after: String) {
    val replacedName = file.name.replace(before, after)
    file.renameTo(File(file.parent, replacedName))
}
