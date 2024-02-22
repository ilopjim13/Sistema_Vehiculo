/**
 * Funcion extendida de Float que permite redondear un Float a 2 decimales
 * @return Float Float redondeado a 2 decimales
 */
fun Float.redondear():Float {
    return (this * 100).toInt().toFloat() / 100
}

fun main() {

    val aurora = Automovil("Aurora", "Seat", "Panda", 50f, 50f * 0.1f, 0f, true) // Coche eléctrico con capacidad de 50 litros, inicia con el 10%
    val boreal = Automovil("Boreal", "BMW", "M8", 80f, 80f * 0.1f, 0f, false) // SUV híbrido con capacidad de 80 litros, inicia con el 10%
    val cefiro = Motocicleta("Céfiro", "Derbi", "Motoreta", 15f, 15f * 0.1f, 0f, 500) // Motocicleta de gran cilindrada con capacidad de 15 litros, inicia con el 10%
    val dinamo = Automovil("Dinamo", "Cintroen", "Sor", 70f, 70f * 0.1f, 0f, true) // Camioneta eléctrica con capacidad de 70 litros, inicia con el 10%
    val eclipse = Automovil("Eclipse", "Renault", "Espacio", 60f, 60f * 0.1f, 0f, false) // Coupé deportivo con capacidad de 60 litros, inicia con el 10%
    val fenix = Motocicleta("Fénix", "Honda", "Vital", 20f, 20f * 0.1f, 0f, 250) // Motocicleta eléctrica con capacidad de 20 litros, inicia con el 10%

    val lista = listOf(aurora,boreal,cefiro,dinamo,eclipse,fenix)

    val carrera = Carrera("La gran carrera", 1000, lista)

    carrera.iniciarCarrera()

    println("*** ${carrera.nombreCarrera.uppercase()} ***\n")

    println("¡Comienza la carrera!")
    println(".....................")
    println("¡Comienza finalizada!\n")

    val ganador = carrera.posiciones.filter{it.value == 1}

    println("¡¡¡ENHORABUENA ${ganador.keys.toString().replace("[", "").replace("]", "").uppercase()}!!!\n")

    println("* Clasificación:\n")

    val resultados = carrera.obtenerResultados()

    resultados.forEach {
        val (nom, pos, km, par, his ) = it
        println("$pos -> ${nom.nombre.replaceFirstChar { it.uppercase() }} ($km)")
    }

    println("")

    resultados.forEach { println(it) }

    println("")

    resultados.forEach { resultado ->
        val (nom, pos, km, par, his ) = resultado
        println("$pos -> ${nom.nombre.replaceFirstChar { it.uppercase() }}")
        resultado.historialAcciones?.forEach { println(it)}
    }

}