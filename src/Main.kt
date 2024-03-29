/**
 * Funcion extendida de Float que permite redondear un Float a 2 decimales
 * @return Float Float redondeado a 2 decimales
 */
fun Float.redondear():Float {
    return (this * 100).toInt().toFloat() / 100
}

fun tiempoEspera() {
    listOf(".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".").forEach {
        print(it)
        Thread.sleep((0.2 * 1000).toLong())
    }
}

fun main() {

    // se crean los vehiculos
    val aurora = Automovil("Aurora", "Seat", "Panda", 50f, 50f * 0.1f, 0f, true) // Coche eléctrico con capacidad de 50 litros, inicia con el 10%
    val boreal = Automovil("Boreal", "BMW", "M8", 80f, 80f * 0.1f, 0f, false) // SUV híbrido con capacidad de 80 litros, inicia con el 10%
    val cefiro = Motocicleta("Céfiro", "Derbi", "Motoreta", 15f, 15f * 0.1f, 0f, 500) // Motocicleta de gran cilindrada con capacidad de 15 litros, inicia con el 10%
    val dinamo = Automovil("Dinamo", "Cintroen", "Sor", 70f, 70f * 0.1f, 0f, true) // Camioneta eléctrica con capacidad de 70 litros, inicia con el 10%
    val eclipse = Automovil("Eclipse", "Renault", "Espacio", 60f, 60f * 0.1f, 0f, false) // Coupé deportivo con capacidad de 60 litros, inicia con el 10%
    val fenix = Motocicleta("Fénix", "Honda", "Vital", 20f, 20f * 0.1f, 0f, 250) // Motocicleta eléctrica con capacidad de 20 litros, inicia con el 10%

    // se crea una lista con todos los participantes
    val lista = listOf(aurora,boreal,cefiro,dinamo,eclipse,fenix)

    // se crea la carrera
    val carrera = Carrera("La gran carrera", 1000, lista)

    carrera.iniciarCarrera() //se inicia la carrea

    println("*** ${carrera.nombreCarrera.uppercase()} ***\n")

    Thread.sleep((0.4 * 1000).toLong())
    println("¡Comienza la carrera!")
    tiempoEspera()
    println("\n¡Carrera finalizada!\n")
    Thread.sleep((0.4 * 1000).toLong())

    val ganador = carrera.posiciones.filter{it.value == 1} //filtra por el ganador que esta en el puesto 1

    println("¡¡¡ENHORABUENA ${ganador.keys.toString().replace("[", "").replace("]", "").uppercase()}!!!\n") // imprime el nombre del ganador
    Thread.sleep((0.4 * 1000).toLong())
    println("* Clasificación:\n")

    val resultados = carrera.obtenerResultados() // se obtienen los resultados de la carrera

    resultados.forEach {resultado ->
        val (nom, pos, km ) = resultado
        println("$pos -> ${nom.nombre.replaceFirstChar { it.uppercase() }} ($km)") // imprime la calificacion
    }

    Thread.sleep((0.5 * 1000).toLong())
    println("")

    resultados.forEach { println(it) } // se imprimen los resultados

    Thread.sleep((0.4 * 1000).toLong())
    println("\nHistorial detallado:\n")
    Thread.sleep((0.4 * 1000).toLong())

    // informacion detallada
    resultados.forEach { resultado ->
        val (nom, pos ) = resultado
        println("$pos -> ${nom.nombre.replaceFirstChar { it.uppercase() }}") // se imprime el nombre con su posicion
        resultado.historialAcciones?.forEach { println(it)} // se imprimen los registros del historial de registros de cada participante
    }

}