package org.itdevexpert.viewmodel.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.combine

/**
 * OBS: Evaluate if migrate to :kmm:coroutine module
 */

/**
 * Combine one instance of #StateFlow<T> as hot
 */
inline fun <reified T1, R> combineState(
    flow1: StateFlow<T1>,
    scope: CoroutineScope = GlobalScope,
    sharingStarted: SharingStarted = SharingStarted.Eagerly,
    crossinline transform: (T1) -> R
): StateFlow<R> = combine(flow1) {
        o1 -> transform.invoke(o1.first())
}.stateIn(scope, sharingStarted, transform.invoke(flow1.value))

/**
 * Combine two instances of #StateFlow<T> as hot
 */
fun <T1, T2, R> combineState(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    scope: CoroutineScope = GlobalScope,
    sharingStarted: SharingStarted = SharingStarted.Eagerly,
    transform: (T1, T2) -> R
): StateFlow<R> = combine(flow1, flow2) {
        o1, o2 -> transform.invoke(o1, o2)
}.stateIn(scope, sharingStarted, transform.invoke(flow1.value, flow2.value))

// -------------

/**
 * ## Returns a [StateFlow<T>] as a _hot_ whose values are generated with [transform] function by combining
 * the most recently emitted values by each state flow.
 *
 * ### Sample:
 *
 * ```
 *    data class A(val a: String)
 *    data class B(val b: Int)
 *
 *    private val test1 = MutableStateFlow(A("a"))
 *    private val test2 = MutableStateFlow(B(2))

 *    @Suppress("CHANGING_ARGUMENTS_EXECUTION_ORDER_FOR_NAMED_VARARGS")
 *    private val _isValidForm = combineStateFlow(
 *        flows = arrayOf(test1, test2),
 *        scope = viewModelScope
 *    ) { combinedFlows: Array<Any> ->
 *        combinedFlows.map {
 *            val doSomething = when (it) {
 *                is A -> true
 *                is B -> false
 *                else -> false
 *            }
 *        }
 *    }
 *
 * ```
 *
 * **Sources:**
 *  - Combine two state flows: https://stackoverflow.com/a/65444578/5279996
 *  - Vararg: https://stackoverflow.com/a/65520425/5279996
 */
@Suppress("CHANGING_ARGUMENTS_EXECUTION_ORDER_FOR_NAMED_VARARGS")
inline fun <reified T, R> combineStateFlow(
    vararg flows: StateFlow<T>,
    scope: CoroutineScope = GlobalScope,
    sharingStarted: SharingStarted = SharingStarted.Eagerly,
    crossinline transform: (Array<T>) -> R
): StateFlow<R> = combine(flows = flows) {
    transform.invoke(it)
}.stateIn(
    scope = scope,
    started = sharingStarted,
    initialValue = transform.invoke(flows.map {
        it.value
    }.toTypedArray())
)