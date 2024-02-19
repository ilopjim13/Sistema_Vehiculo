open class Vehiculo(val marca:String, val modelo: String, capacidadCombustible: Float, combustibleActual: Float, var kilometrosActuales:Int) {

    val capacidadCombustible = capacidadCombustible.redondear()
    var combustibleActual = combustibleActual.redondear()
    open val KM_L = 10

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

    open fun calcularAutonomia() = (combustibleActual * KM_L).toInt()

    open fun realizarViaje(distancia:Int) :Int {
        val distanciaRecorrida = this.calcularAutonomia()
        val distanciaRestante:Int
        if (distanciaRecorrida < distancia) {
            distanciaRestante = distancia - distanciaRecorrida
            this.combustibleActual = 0.0f
            this.kilometrosActuales += distanciaRecorrida
        }
        else {
            distanciaRestante = 0
            this.combustibleActual -= distancia / this.KM_L
            this.kilometrosActuales += distancia
        }
        return distanciaRestante
    }

    fun repostar(cantidad:Float = 0.0f) :Float {
        val cantRepostar :Float
        return if (cantidad <= 0.0) {
            cantRepostar = this.combustibleActual - capacidadCombustible
            this.combustibleActual = capacidadCombustible
            cantRepostar
        } else if (cantidad + combustibleActual >= capacidadCombustible) {
            cantRepostar = this.combustibleActual - capacidadCombustible
            this.combustibleActual = capacidadCombustible
            cantRepostar
        } else {
            this.combustibleActual += cantidad
            cantidad
        }

    }
}