open class Vehiculo(val marca:String, val modelo: String, val capacidadCombustible: Float, val combustibleActual: Float, val kilometrosActuales:Int) {

    fun obtenerInformacion(): String {
        return "El vehiculo puede recorrer ${combustibleActual * 10} kilometros con $combustibleActual litros de combustible."
    }

    override fun toString():String {
        return "$marca $modelo con una capacidad de $capacidadCombustible"
    }

    open fun calcularAutonomia() = capacidadCombustible * 10

    fun realizarViaje(distancia:Int) :Int {
        var distanciaRecorrida = 0
        var distanciaRestante = 0
        if (combustibleActual * 10 - distancia < 0) {
            distanciaRecorrida = combustibleActual.toInt() * 10
            distanciaRestante =
        }
        else distanciaRecorrida = combustibleActual.toInt() * 10 - distancia

        println("Ha viajado $viajado km")
        return distanciaRestante
    }

    fun repostar(cantidad:Float) :Float {

    }
}