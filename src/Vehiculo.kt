open class Vehiculo(val marca:String, val modelo: String, capacidadCombustible: Float, combustibleActual: Float, var kilometrosActuales:Float) {

    val capacidadCombustible = capacidadCombustible.redondear()
    var combustibleActual = combustibleActual.redondear()
    companion object {
        var KM_L = 10.0f
    }


    init {
        require(this.capacidadCombustible > 0) {"La capacidad no puede ser menor a 0"}
        require(this.combustibleActual > 0) {"El combustible no puede ser menor a 0"}
    }

    fun obtenerInformacion(): String {
        return "El vehiculo puede recorrer ${calcularAutonomia()} kilometros con $combustibleActual litros de combustible."
    }

    override fun toString():String {
        return "$marca $modelo con una capacidad de $capacidadCombustible, kilometros actuales: $kilometrosActuales"
    }

    open fun calcularAutonomia() = (combustibleActual * KM_L).redondear()

    open fun realizarViaje(distancia:Float) :Float {
        val distanciaRecorrida = this.calcularAutonomia()
        val distanciaRestante:Float
        if (distanciaRecorrida < distancia) {
            distanciaRestante = distancia - distanciaRecorrida
            this.combustibleActual = 0.0f
            this.kilometrosActuales += distanciaRecorrida
        }
        else {
            distanciaRestante = 0.0f
            this.combustibleActual -= distancia / KM_L
            this.kilometrosActuales += distancia
        }
        return distanciaRestante.redondear()
    }

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