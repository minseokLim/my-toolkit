package com.minseoklim.util

import org.jsoup.Jsoup
import java.io.File

fun File.extractAllTextsFromHtml(): Set<String> {
    val document = Jsoup.parse(this)
    return document.allElements
        .filter { elem -> elem.hasText() }
        .map { it.text() }
        .toSet()
}

private val textInJsRegex = "'([^']*)'|\"([^\"]*)\"".toRegex()

fun File.extractAllTextsFromHtmlJs(): Set<String> {
    val document = Jsoup.parse(this)
    val elements = document.getElementsByTag("script")

    return elements.flatMap { element ->
        textInJsRegex.findAll(element.html()).map { it.groupValues[1].ifBlank { it.groupValues[2] } }
    }.toSet()
}

fun File.replaceTexts(replaceMap: Map<String, String>) {
    replaceMap.entries.fold(this.readText()) { acc, entry ->
        acc.replace(entry.key, entry.value)
    }.let {
        this.writeText(it)
    }
}
