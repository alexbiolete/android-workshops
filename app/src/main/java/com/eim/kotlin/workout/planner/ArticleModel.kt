package com.eim.kotlin.workout.planner

data class ArticleModel(
    val source: Any,
    val author: String? = "Author",
    val title: String = "Title",
    val description: String = "Description"
)
