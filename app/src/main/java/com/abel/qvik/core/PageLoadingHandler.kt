package com.abel.qvik.core

abstract class PageLoadingHandler {

    private val loadingThreshold: Int = ITEMS_BEFORE_LOADING

    private var isLoadingPage: Boolean = false

    /**
     * Called when the next page should be loaded. Called from UI thread, so keep it short.
     */
    abstract fun onLoadNextPage()

    var hasNextPage: Boolean = false
        set(value) {
            field = value
            isLoadingPage = false
        }

    /**
     * Checks if the handler should load the next page
     *
     * @param position Current position of the adapter
     * @param totalItemCount Total number of items in the adapter
     */
    fun checkForNewPage(
        position: Int,
        totalItemCount: Int
    ) {
        //If we are not loading anything, and there are more pages
        if (!isLoadingPage && hasNextPage) {
            if (totalItemCount > 0 && position >= totalItemCount - loadingThreshold) {
                isLoadingPage = true
                onLoadNextPage()
            }
        }
    }

    companion object {
        const val ITEMS_BEFORE_LOADING = 10
    }
}
