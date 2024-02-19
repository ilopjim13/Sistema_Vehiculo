class Automovil(marca:String,
                modelo:String,
                capacidadCombustible:Float,
                combustibleActual:Float,
                kilometrosActuales:Int,
                var esElectrico :Boolean
) :Vehiculo(marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    override val KM_L = 10

    companion object {
        var conducionBritanica:Boolean = true
        fun cambiarCondicionBritania(nuevaCondicion: Boolean) {
            conducionBritanica = nuevaCondicion
        }
    }

    override fun calcularAutonomia(): Int {
        return if(!esElectrico) super.calcularAutonomia()
        else (this.combustibleActual * 15).toInt()
    }

    fun realizaDerrape():Float {
        println("El automovil ha realizado un derrape.")
        if (esElectrico) this.combustibleActual -= 0.33f
        else this.combustibleActual -= 0.5f
        return this.combustibleActual
    }

    override fun toString(): String {
        return "${super.toString()}, ${
            if (esElectrico) "es electrico"
            else "no es electrico"
        }, y la condución ${
            if (conducionBritanica) "es Británica"
            else "no es Británica"
        }."
    }
}