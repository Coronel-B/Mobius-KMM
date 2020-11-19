package app.mobius.mobius

/**
 * PRO: Permite que una clase pueda ser observada
 * OBS: Esta clase observable contiene a los observadores y un tipo genérico para el valor que se
 * enviará a estos observadores. Cuando el valor cambia los observadores son notificados.
 */
class Observable<T> {

    private var observers = emptyList<(T) -> Unit>()

    fun addObserver(observer: (T) -> Unit) {
        observers = observers + observer
    }

    fun clearObservers() {
        observers = emptyList()
    }

    fun callObservers(newValue: T) {
        observers.forEach {
            it(newValue)
        }
    }

}