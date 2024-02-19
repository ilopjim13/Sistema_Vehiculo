open class Vehiculo(val marca:String, val modelo: String, val capacidadCombustible: Float, var combustibleActual: Float, val kilometrosActuales:Int) {

    fun obtenerInformacion(): String {
        return "El vehiculo puede recorrer ${combustibleActual * 10} kilometros con $combustibleActual litros de combustible."
    }

    override fun toString():String {
        return "$marca $modelo con una capacidad de $capacidadCombustible"
    }

    open fun calcularAutonomia() = capacidadCombustible * 10

    fun realizarViaje(distancia:Int) :Int {
        val distanciaRecorrida = combustibleActual.toInt() * 10
        val distanciaRestante:Int
        if (combustibleActual * 10 < distancia) {
            distanciaRestante = distanciaRecorrida - distancia
            this.combustibleActual = 0.0f
        }
        else {
            distanciaRestante = 0
            this.combustibleActual -= distancia * 10
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