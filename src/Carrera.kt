import kotlin.random.Random

class Carrera(val nombreCarrera: String,
              val distanciaTotal: Int,
              val participantes:List<Vehiculo>) {

    val historialAcciones: MutableMap<String, MutableList<String>> = mutableMapOf()
    val posiciones: MutableMap<String, Int> = mutableMapOf()

    var estadoCarrera:Boolean = false

    val accion = mutableListOf<String>()

    fun iniciarCarrera() {
        this.estadoCarrera = true


    }

    fun avanzarVehiculo(vehiculo: Vehiculo) {
        val avanzado = vehiculo.realizarViaje((Random(10).nextInt(200).toFloat()))

        while ( avanzado != 0.0f) {
            vehiculo.repostar()
            vehiculo.realizarViaje(avanzado)
        }
    }

    fun repostarVehiculos(vehiculo: Vehiculo, cantidad: Float) {
        val cant = vehiculo.repostar(cantidad)
        accion.add("${vehiculo.nombre} ha repostado una cantidad de $cant litros")
        historialAcciones[vehiculo.nombre] = accion
    }

    fun realizarFiligrana(vehiculo: Vehiculo) {
        when (vehiculo) {
            is Automovil -> vehiculo.realizaDerrape()
            is Motocicleta -> vehiculo.realizoCaballito()
        }
    }

    fun actualizarPosiciones() {

    }

}