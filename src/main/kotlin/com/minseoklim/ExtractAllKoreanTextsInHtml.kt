package com.minseoklim

import com.minseoklim.util.extractAllIncludedFiles
import com.minseoklim.util.extractAllTextsFromHtml
import com.minseoklim.util.extractAllTextsFromHtmlJs
import java.io.File

fun main() {
    val targetDirectory =
        File("/Users/minseoklim/workspace/study/practice/toy-project-2024/frontend/src/main/resources/templates")
    val targetFiles = targetDirectory.extractAllIncludedFiles()
    extractAllKoreanTexts(targetFiles).forEach { println(it) }
}

private fun extractAllKoreanTexts(files: List<File>): List<String> {
    val texts = files.flatMap { it.extractAllTextsFromHtml() + it.extractAllTextsFromHtmlJs() }.toSet()
    val koreanRegex = ".*[가-힇].*".toRegex()
    return texts.filter { koreanRegex.matches(it) }
}
