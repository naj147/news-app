package com.abel.cache

import com.abel.common.model.NewsCategory

// List of categories spotted in the UI design
val followedCategories = listOf(
    NewsCategory("https://i.imgur.com/aZGiYMx.png","321k","fashion"),
    NewsCategory("https://i.imgur.com/T96WYUX.png","120k","science"),
    NewsCategory("https://i.imgur.com/sAVZmrZ.png","1.1m","auto"),
    NewsCategory("https://i.imgur.com/vxHawXo.png","80.7k","technology"),
    NewsCategory("https://i.imgur.com/CbxMfmu.png","2.7M","entertainment"),
    NewsCategory("https://i.imgur.com/mwR9nEW.png","800.8k","environment"),
    NewsCategory("https://i.imgur.com/W4HdbTi.png","67.3k","finances"),
    NewsCategory("https://i.imgur.com/6Nergds.png","1.9m","travel")
)
/* LIST OF CATEGORIES SUPPORTED BY THE API
   API Documentation (https://newsapi.org/docs/endpoints/top-headlines)
 */
val availableCategories =
    listOf("general", "science", "health", "technology", "business", "sports", "entertainment")
