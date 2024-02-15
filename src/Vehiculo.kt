open class Vehiculo(val marca:String, val modelo: String, val capacidadCombustible: Float, val combustibleActual: Float, val kilometrosActuales:Int) {

    fun obtenerInformacion(): String {
        return "El vehiculo puede recorrer ${combustibleActual * 10} kilometros con $combustibleActual litros de combustible."
    }

    open fun calcularAutonomia() = capacidadCombustible * 10

    fun realizarViaje(distancia:Int) :Int {

    }

    fun repostar(cantidad:Float) :Float {

    }
}