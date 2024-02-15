class Automovil(marca:String,
                modelo:String,
                capacidadCombustible:Float,
                combustibleActual:Float,
                kilometrosActuales:Int,
                val esElectrico :Boolean,
                val conducionBritanica:Boolean
) :Vehiculo(marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    override fun calcularAutonomia(): Float {

    }
}