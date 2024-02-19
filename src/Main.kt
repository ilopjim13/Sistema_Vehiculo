import kotlin.math.roundToInt

fun Float.redondear():Float {
    return (this * 100).toInt().toFloat() / 100
}

fun main() {

    val automovil = Automovil("", "", 100.0f, 25.0f, 10, false)
    val automovil2 = Automovil("", "", 5.0f, 3.0f, 10, true)
    val automovil3 = Automovil("", "", 250.0f, 3.0f, 10, true)


    println(automovil.combustibleActual)
    automovil.realizaDerrape()
    println(automovil.combustibleActual)

    println("\nrealiza viaje")
    println(automovil.realizarViaje(250))
    println(automovil.kilometrosActuales)
    println(automovil.combustibleActual)
}