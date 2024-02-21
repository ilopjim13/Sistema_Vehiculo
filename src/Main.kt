/**
 * Funcion extendida de Float que permite redondear un Float a 2 decimales
 * @return Float Float redondeado a 2 decimales
 */
fun Float.redondear():Float {
    return (this * 100).toInt().toFloat() / 100
}

fun main() {

    val automovil = Automovil("soio","", "", 100.0f, 25.0f, 10.8789f, true)
    val automovil2 = Automovil("s","", "", 5.0f, 3.0f, 10f, true)
    val moto = Motocicleta("os","", "", 250.87987f, 1.5555f, 10.879879f, 150)

    println(moto.calcularAutonomia())
    println(moto.combustibleActual)
    println(moto.realizoCaballito())
    println(moto.combustibleActual)
    println(moto.realizarViaje(5.999f))
    println(moto.combustibleActual)
    println(moto.kilometrosActuales)
    println("Moto\n")


    println(automovil.combustibleActual)
    automovil.realizaDerrape()
    println(automovil.combustibleActual)

    println("\nrealiza viaje")
    println(automovil.realizarViaje(200f))
    println(automovil.kilometrosActuales)
    println(automovil.combustibleActual)
}