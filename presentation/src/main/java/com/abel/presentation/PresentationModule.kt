package com.abel.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.definition.Definition
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

const val COMPUTATION_CONTEXT = "COMPUTATION_CONTEXT"

@ExperimentalCoroutinesApi
val presentationModule = module {
    factory<CoroutineContext>(named(COMPUTATION_CONTEXT)) { Dispatchers.Default }
}

inline fun <reified VS : BaseViewState, reified VM : BaseViewModel<VS>> Module.baseViewModel(
    name: String? = null,
    override: Boolean = false,
    noinline definition: Definition<VM>
) {
    viewModel(name?.let(::named), override, definition)
}
