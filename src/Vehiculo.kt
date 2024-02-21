/**
 * Clase abierta padre Vehiculo
 * @property marca :String marca del vehiculo
 * @property modelo :String modelo del vehiculo
 * @param capacidadCombustible :Float cantidad máxima del combustible que tiene el vehiculo
 * @param combustibleActual :Float cantidad de combustible actual que tiene el vehiculo
 * @property kilometrosActuales :Float kilometros actuales que ha recorrido el vehiculo
 */
open class Vehiculo(nombre:String, val marca:String, val modelo: String, capacidadCombustible: Float, combustibleActual: Float, var kilometrosActuales:Float) {

    val nombre:String = nombre.trim().lowercase()
    val capacidadCombustible = capacidadCombustible.redondear()
    var combustibleActual = combustibleActual.redondear()
        set(value) {
            field = value.redondear()
        }

    init {
        require(this.capacidadCombustible > 0) {"La capacidad no puede ser menor a 0"}
        require(this.combustibleActual > 0) {"El combustible no puede ser menor a 0"}
        require(this.nombre.isNotBlank()) {"El nombre no puede estar vacío."}
        require(!agregarNombres(this.nombre)) {"El nombre no se puede repetir."}
    }

    companion object {
        const val KM_L = 10.0f
        private val nombres = mutableSetOf<String>()
        private fun agregarNombres(nombre:String) = !nombres.add(nombre)
    }


    /**
     * Muestra la información de los kilometros que puede recorrer el vehiculo con la cantidad de combustible que tiene
     * @return String retorna la información correspondiente
     */
    fun obtenerInformacion() = "El vehiculo puede recorrer ${calcularAutonomia()} kilometros con $combustibleActual litros de combustible."


    /**
     * Muestra la información de los vehiculos
     * @return String retorna la información correspondiente
     */
    override fun toString():String {
        return "$marca $modelo con una capacidad de $capacidadCombustible, kilometros actuales: $kilometrosActuales"
    }


    /**
     * Calcula la autonomia del vehiculo con el combustible actual y la retorna
     * @return Float retorna el calculo de la autonomia
     */
    open fun calcularAutonomia() = (combustibleActual * KM_L).redondear()

    /**
     * Realiza un viaje dependiendo de la distancia introducida y retornará la distancia restante
     * @return Float retorna la distancia restante para llegar a la distancia introducida
     */
    open fun realizarViaje(distancia:Float) :Float {
        val distanciaRecorrida = this.calcularAutonomia()
        val distanciaRestante:Float
        if (distanciaRecorrida < distancia) {
            distanciaRestante = distancia - distanciaRecorrida
            this.combustibleActual = 0.0f
            this.kilometrosActuales += distanciaRecorrida.redondear()
        }
        else {
            distanciaRestante = 0.0f
            this.combustibleActual -= (distancia / KM_L).redondear()
            this.kilometrosActuales = this.kilometrosActuales.redondear() + distancia.redondear()
        }
        return distanciaRestante.redondear()
    }


    /**
     * Reposta el vehiculo, si no se le pasa nada por parametro o si supera la capacidad del combustible al sumarlo se le llenará
     * el combustible actual hasta la capacidad máxima y si no suma la cantidad introducida con la cantidad actual.
     * @return Float retorna la cantidad repostada.
     */
    fun repostar(cantidad:Float = 0.0f) :Float {
        val cantRepostar :Float
        return if (cantidad <= 0.0) {
            cantRepostar = this.combustibleActual - capacidadCombustible
            this.combustibleActual = capacidadCombustible
            cantRepostar.redondear()
        } else if (cantidad + combustibleActual >= capacidadCombustible) {
            cantRepostar = this.combustibleActual - capacidadCombustible
            this.combustibleActual = capacidadCombustible
            cantRepostar.redondear()
        } else {
            this.combustibleActual += cantidad
            cantidad.redondear()
        }

    }
}