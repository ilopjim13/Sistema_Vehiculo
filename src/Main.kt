/**
 * Funcion extendida de Float que permite redondear un Float a 2 decimales
 * @return Float Float redondeado a 2 decimales
 */
fun Float.redondear():Float {
    return (this * 100).toInt().toFloat() / 100
}

/**
 * Funcion extendida de String que permite eliminar los espacios sobrantes al principio y fina de la cadena de caracteres
 * y capitaliza todas las palabras seguida de otra
 * @return String retorna el string sin espacios a la izquierda y derecha y las palabras capitalizadas
 */
fun String.espacios(): String {
    val espacios = this.split(" ").toMutableList()
    val palabras:MutableList<String> = mutableListOf()
    espacios.forEach{if (it.isNotBlank()) palabras.add(it)}
    return palabras.joinToString(" ") { i -> i.replaceFirstChar { it.uppercase() } }
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

    println("¡Comienza la carrera!")
    println(".....................")
    println("¡Carrera finalizada!\n")

    val ganador = carrera.posiciones.filter{it.value == 1} //filtra por el ganador que esta en el puesto 1

    println("¡¡¡ENHORABUENA ${ganador.keys.toString().replace("[", "").replace("]", "").uppercase()}!!!\n") // imprime el nombre del ganador

    println("* Clasificación:\n")

    val resultados = carrera.obtenerResultados() // se obtienen los resultados de la carrera

    resultados.forEach {resultado ->
        val (nom, pos, km ) = resultado
        println("$pos -> ${nom.nombre.espacios()} ($km)") // imprime la calificacion
    }

    println("")

    resultados.forEach { println(it) } // se imprimen los resultados

    println("")

    // informacion detallada
    resultados.forEach { resultado ->
        val (nom, pos ) = resultado
        println("$pos -> ${nom.nombre.espacios()}") // se imprime el nombre con su posicion
        resultado.historialAcciones?.forEach { println(it)} // se imprimen los registros del historial de registros de cada participante
    }

}