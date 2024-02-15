class Automovil(marca:String,
                modelo:String,
                capacidadCombustible:Float,
                combustibleActual:Float,
                kilometrosActuales:Int,
                val esElectrico :Boolean,
                var conducionBritanica:Boolean
) :Vehiculo(marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    override fun calcularAutonomia(): Float {

    }

    fun cambiarCondicionBritania(nuevaCondicion: Boolean) {
        this.conducionBritanica = nuevaCondicion
    }

    fun realizaDerrape():Float {

    }
}