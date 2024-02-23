/**
 * Clase carrera que ejecuta carreras entre los diferentes vehiculos
 * @property nombreCarrera: String nombre de la carrera para identificación.
 * @property distanciaTotal: Float distancia total que los vehículos deben recorrer para completar la carrera.
 * @property participantes: List<Vehiculo> lista que contiene todos los vehículos participantes en la carrera.
 * @property historialAcciones: MutableMap<String, MutableList<String>> mapa para registrar el historial de acciones de cada vehículo. La clave es el nombre del vehículo y el valor es una lista de strings describiendo sus acciones.
 * @property posiciones: MutableMap<String, Int> diccionario para mantener un registro de la posición con el nombre y el numero del puesto del participante
 * @property estadoCarrera: Boolean indicador de si la carrera está en curso o ha finalizado.
 */
class Carrera(val nombreCarrera: String,
              private val distanciaTotal: Int,
              private val participantes:List<Vehiculo>) {

    private val historialAcciones: MutableMap<String, MutableList<String>> = mutableMapOf()
    var posiciones: MutableMap<String, Int> = mutableMapOf()

    private var estadoCarrera:Boolean = false

    init {
        require(this.distanciaTotal >= 1000) {"La distancia total no puede ser menor a 1000 km"}
    }

    /**
     * Inicia la carrera, estableciendo estadoCarrera a true y comenzando el ciclo de iteraciones
     * donde los vehículos avanzan y realizan acciones.
     */
    fun iniciarCarrera() {
        this.estadoCarrera = true

        while (estadoCarrera) {

            val vehiculo = participantes.random()

            avanzarVehiculo(vehiculo)

            actualizarPosiciones()

            determinarGanador()
        }

    }

    /**
     * Hace avanzar al vehiculo una distancia aleatoria entre 10 y 200 km. Si el vehículo necesita repostar,
     * se llama al método repostarVehiculos() antes de que pueda continuar. Este método llama a realizar filigranas.
     * @param vehiculo :Vehiculo vehiculo que va a avanzar
     */
    private fun avanzarVehiculo(vehiculo: Vehiculo) {
        val avanzar = (1000..20000).random().toFloat()/100
        var avanzado = avanzar.redondear()

        registrarAccion(vehiculo.nombre, "Inicia viaje: A recorrer $avanzar (${vehiculo.kilometrosActuales} kms y ${vehiculo.combustibleActual} L actuales)")
        while (avanzado > 0f) {

            val random = (0..2).random()
            comprobarCombustible(vehiculo) // comprueba el combustible para ver si puede avanzar con el que tiene

            if (vehiculo.kilometrosActuales < distanciaTotal) // si los kilometros actuales es menor a la distancia total se ejecuta si no el vehiculo habra ganado
                if (avanzado >= 20) {
                    avanzado -= 20f
                    vehiculo.realizarViaje(20f).redondear() // avanza el vehiculo 20 y resta 20 a la distancia avanzada
                    registrarAccion(vehiculo.nombre, "Avanza tramo: Recorrido 20.0 kms")
                    comprobarCombustible(vehiculo) // vuelve a comprobar si tiene suficiente combustible para realizar las filigranas

                    when(random) { // dependiendo del numero que salga lo hara una vez, dos o ninguna si sale 0
                        1 -> realizarFiligrana(vehiculo)
                        2 ->  {
                            realizarFiligrana(vehiculo)
                            realizarFiligrana(vehiculo)
                        }
                    }

                } else { // si no es mayor de 20 avanza con la distancia restante
                    vehiculo.realizarViaje(avanzado.redondear())
                    registrarAccion(vehiculo.nombre, "Avanza tramo: Recorrido ${avanzado.redondear()} kms")
                    avanzado = 0f
                }
            else {
                vehiculo.kilometrosActuales = distanciaTotal.toFloat()
                avanzado = 0f
            }
        }

        registrarAccion(vehiculo.nombre, "Finaliza viaje: Total Recorrido $avanzar (${vehiculo.kilometrosActuales} kms y ${vehiculo.combustibleActual} L actuales)")

    }

    /**
     * Comprueba el combustible del vehiculo para ver si necesita repostar o puede seguir avanzando sin haerlo
     * @param vehiculo :Vehiculo vehiculo que va a comprobar su combustible
     */
    private fun comprobarCombustible(vehiculo: Vehiculo) { if (vehiculo.calcularAutonomia() - 20 <= 0) repostarVehiculos(vehiculo, 0f) }


    /**
     * Reposta el vehículo seleccionado, incrementando su combustibleActual y registrando la acción en historialAcciones.
     * @param vehiculo :Vehiculo vehiculo que va a repostar
     * @param cantidad: Float cantidad que se va a repostar
     */
    private fun repostarVehiculos(vehiculo: Vehiculo, cantidad: Float) {
        vehiculo.repostar(cantidad)
        registrarAccion(vehiculo.nombre, "Repostaje tramo: ${vehiculo.combustibleActual} L")
    }

    /**
     * Determina aleatoriamente si un vehículo realiza una filigrana (derrape o caballito) y registra la acción.
     * @param vehiculo :Vehiculo vehiculo que va a realizar una filigrana
     */
    private fun realizarFiligrana(vehiculo: Vehiculo) {
        when (vehiculo) {
            is Automovil -> {
                vehiculo.realizaDerrape()
                registrarAccion(vehiculo.nombre, "Derrape: Combustible restante ${vehiculo.combustibleActual} L")
            }
            is Motocicleta -> {
                vehiculo.realizoCaballito()
                registrarAccion(vehiculo.nombre, "Caballito: Combustible restante ${vehiculo.combustibleActual} L")

            }
        }
    }

    /**
     * Actualiza posiciones con los kilómetros recorridos por cada vehículo después de cada iteración, manteniendo un seguimiento de la competencia.
     */
    private fun actualizarPosiciones() {
        var cont = 0
        var orden: MutableList<Pair<String, Int>> = mutableListOf() // se crea una lista de pares vacia
        posiciones = mutableMapOf() // pongo vacia el diccionario de posiciones
        participantes.forEach { orden.add(Pair(it.nombre, it.kilometrosActuales.toInt()))} // añado el nombre y los kilometros a la lista de pares
        orden = orden.sortedByDescending { it.second }.toMutableList() // la ordeno por orden descendente
        orden.forEach {posiciones[it.first] = ++cont} // la añado al diccionario con el nombre como clave primaria y el numero de la posicion
    }

    /**
     * Revisa posiciones para identificar al vehículo (o vehículos) que haya alcanzado o superado la distanciaTotal, estableciendo el estado de la carrera a finalizado y determinando el ganador.
     */
    private fun determinarGanador() {
        var comprobar:Vehiculo?
        var primero: Vehiculo? = null

        posiciones.forEach {(key, valor) ->
            comprobar = participantes.find { it.nombre == key && valor == 1} // encuentro el vehiculo que esta en la posicion 1
            if (comprobar != null) primero = comprobar // comprueba si no es nulo y le da a primero el vehiculo correspondiente
        }

        if(primero!!.kilometrosActuales >= distanciaTotal) estadoCarrera = false // si ha llegado a la distancia total pone el estado como false y termina la carrera


    }

    /**
     * Devuelve una clasificación final de los vehículos, cada elemento tendrá el nombre del vehiculo,
     * posición ocupada, la distancia total recorrida, el número de paradas para repostar y el historial de acciones.
     * La collección estará ordenada por la posición ocupada.
     * @return List<ResultadoCarrera> retorna el resultado de la carrera
     */
    fun obtenerResultados() :List<ResultadoCarrera> {

        val lista = mutableListOf<ResultadoCarrera>()

        posiciones.forEach {(key, valor) ->
            val vehiculo = participantes.find { it.nombre == key} // le doy a vehiculo el vehiculo

            if (vehiculo != null) {
                lista.add(ResultadoCarrera(vehiculo, valor, vehiculo.kilometrosActuales, vehiculo.paradas, this.historialAcciones[vehiculo.nombre]?.toList())) // añado  a la lista una instancia de tipo ResultadoCarrea
            }
        }
        return lista
    }

    /**
     * Añade una acción al historialAcciones del vehículo especificado.
     * @param vehiculo: String vehiculo al que se le va a registrar la accion
     * @param accion: String accion que se va a guardar en la lista del participante
     */
    private fun registrarAccion(vehiculo: String, accion: String) {
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