

class Carrera(val nombreCarrera: String,
              val distanciaTotal: Int,
              val participantes:List<Vehiculo>) {

    val historialAcciones: MutableMap<String, MutableList<String>> = mutableMapOf()
    var posiciones: MutableMap<String, Int> = mutableMapOf()

    var estadoCarrera:Boolean = false

    fun iniciarCarrera() {
        this.estadoCarrera = true

        while (estadoCarrera) {

            val vehiculo = participantes.random()

            avanzarVehiculo(vehiculo)

            actualizarPosiciones()

            determinarGanador()
        }

    }

    fun avanzarVehiculo(vehiculo: Vehiculo) {
        var avanzar = (1000..20000).random().toFloat()/100
        var avanzado: Float

        val filigranas = (avanzar / 20).toInt()

        registrarAccion(vehiculo.nombre, "Inicia viaje: A recorrer $avanzar (${vehiculo.kilometrosActuales} kms y ${vehiculo.combustibleActual} L actuales)")


        repeat (filigranas) {
            if (vehiculo.combustibleActual <= 0) {
                repostarVehiculos(vehiculo, 0f)
                registrarAccion(vehiculo.nombre, "Repostaje tramo: Recorrido $")
            }
             avanzar = vehiculo.realizarViaje(avanzar)
            registrarAccion(vehiculo.nombre, "Avanza tramo: Recorrido")

            realizarFiligrana(vehiculo)
        }

        registrarAccion(vehiculo.nombre, "Finaliza viaje: Total Recorrido $avanzar (${vehiculo.kilometrosActuales} kms y ${vehiculo.combustibleActual} L actuales)")

    }

    fun repostarVehiculos(vehiculo: Vehiculo, cantidad: Float) {
        val cant = vehiculo.repostar(cantidad)
    }

    fun realizarFiligrana(vehiculo: Vehiculo) {
        when (vehiculo) {
            is Automovil -> {
                val com = vehiculo.realizaDerrape()
                registrarAccion(vehiculo.nombre, "Derrape: Combustible restante ${vehiculo.combustibleActual}")
            }
            is Motocicleta -> {
                val com = vehiculo.realizoCaballito()
                registrarAccion(vehiculo.nombre, "Caballito: Combustible restante ${vehiculo.combustibleActual}")

            }
        }
    }

    fun actualizarPosiciones() {
        var cont = 0
        var orden: MutableList<Pair<String, Int>> = mutableListOf()
        posiciones = mutableMapOf()
        participantes.forEach { orden.add(Pair(it.nombre, it.kilometrosActuales.toInt()))}
        orden = orden.sortedByDescending { it.second }.toMutableList()
        orden.forEach {posiciones[it.first] = ++cont}
    }

    fun determinarGanador() {
        var comprobar:Vehiculo? = null
        var ganador: Vehiculo? = null
        posiciones.forEach {(key, valor) ->
            comprobar = participantes.find { it.nombre == key && valor == 1}
            if (comprobar != null) ganador = comprobar
        }

        if(ganador!!.kilometrosActuales >= distanciaTotal) estadoCarrera = false


    }

    fun obtenerResultados() :List<ResultadoCarrera> {

        val lista = mutableListOf<ResultadoCarrera>()

        posiciones.forEach {(key, valor) ->
            val vehiculo = participantes.find { it.nombre == key}

            if (vehiculo != null) {
                lista.add(ResultadoCarrera(vehiculo, valor, vehiculo.kilometrosActuales, vehiculo.paradas, this.historialAcciones[vehiculo.nombre]?.toList()))
            }
        }
        return lista
    }

    fun registrarAccion(vehiculo: String, accion: String) {
        if (historialAcciones.containsKey(vehiculo)) this.historialAcciones[vehiculo]?.add(accion)
        else this.historialAcciones[vehiculo] = mutableListOf(accion)

    }


    /**
     * Representa el resultado final de un vehículo en la carrera, incluyendo su posición final, el kilometraje total recorrido,
     * el número de paradas para repostar, y un historial detallado de todas las acciones realizadas durante la carrera.
     *
     * @property vehiculo El [Vehiculo] al que pertenece este resultado.
     * @property posicion La posición final del vehículo en la carrera, donde una posición menor indica un mejor rendimiento.
     * @property kilometraje El total de kilómetros recorridos por el vehículo durante la carrera.
     * @property paradasRepostaje El número de veces que el vehículo tuvo que repostar combustible durante la carrera.
     * @property historialAcciones Una lista de cadenas que describen las acciones realizadas por el vehículo a lo largo de la carrera, proporcionando un registro detallado de su rendimiento y estrategias.
     */
    data class ResultadoCarrera(
        val vehiculo: Vehiculo,
        val posicion: Int,
        val kilometraje: Float,
        val paradasRepostaje: Int,
        val historialAcciones: List<String>?
    )


}