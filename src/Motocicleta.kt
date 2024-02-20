class Motocicleta(marca:String,
                  modelo:String,
                  capacidadCombustible:Float,
                  combustibleActual:Float,
                  kilometrosActuales:Float,
                  val cilindrada :Int
) :Vehiculo(marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {



    override fun calcularAutonomia() : Float {
        KM_L = 20.0f
        return (combustibleActual * KM_L).redondear()
    }


    fun realizoCaballito() : Float {
        println("La motocicleta ha realizado un caballito.")
        this.combustibleActual -= 0.25f
        return this.combustibleActual.redondear()
    }

}