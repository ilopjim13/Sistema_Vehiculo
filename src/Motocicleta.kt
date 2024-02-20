class Motocicleta(marca:String,
                  modelo:String,
                  capacidadCombustible:Float,
                  combustibleActual:Float,
                  kilometrosActuales:Int,
                  val cilindrada :Int
) :Vehiculo(marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    override val KM_L = 20

    override fun calcularAutonomia() = (combustibleActual * KM_L).toInt()


    fun realizoCaballito() : Float {
        println("La motocicleta ha realizado un caballito.")
        this.combustibleActual -= 0.25f
        return this.combustibleActual
    }

}