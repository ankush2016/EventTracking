package com.tools.eventtrackinglib.model

data class RatingModel(
    val rulesList: ArrayList<Int>,
    val headerText: String? = null,
    val descriptionText: String? = null,
    val thanksDialogTitle: String? = null,
    val thanksDialogSubtitle: String? = null,
    val applicationId: String
)