package com.abel.cache

import com.abel.common.model.NewsCategory

// List of categories spotted in the UI design
val followedCategories = listOf(
    NewsCategory("https://i.imgur.com/aZGiYMx.png",300000,"fashion"),
    NewsCategory("https://i.imgur.com/T96WYUX.png",300000,"science"),
    NewsCategory("https://i.imgur.com/sAVZmrZ.png",300000,"auto"),
    NewsCategory("https://i.imgur.com/vxHawXo.png",300000,"technology"),
    NewsCategory("https://i.imgur.com/CbxMfmu.png",300000,"entertainment"),
    NewsCategory("https://i.imgur.com/mwR9nEW.png",300000,"environment"),
    NewsCategory("https://i.imgur.com/W4HdbTi.png",300000,"finances"),
    NewsCategory("https://i.imgur.com/6Nergds.png",300000,"travel")
)
/* LIST OF CATEGORIES SUPPORTED BY THE API
   API Documentation (https://newsapi.org/docs/endpoints/top-headlines)
 */
val availableCategories =
    listOf("general", "science", "health", "technology", "business", "sports", "entertainment")
