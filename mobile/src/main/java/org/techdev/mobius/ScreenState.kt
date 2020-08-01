package org.techdev.mobius

/**
 * PRO: Describe models that represent the status of the UI
 * PRE: The way you use the model depends entirely on the view.
 * OBS: Hay estados genéricos que se aplican a todas las pantallas.
 * Esta clase es usada al no tener una comunicación directa con la vista.
 * Por ej. para mostrar el progreso se envia el estado Loading.
 * @param T: tipo genérico que representa el estado específico que necesita la view
 */
sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    object Offline : ScreenState<Nothing>()
    object Timeout : ScreenState<Nothing>()
    object Error : ScreenState<Nothing>()
    class Render<T>(val renderState: T) : ScreenState<T>()
}