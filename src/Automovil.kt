class Automovil(marca:String,
                modelo:String,
                capacidadCombustible:Float,
                combustibleActual:Float,
                kilometrosActuales:Float,
                var esElectrico :Boolean
) :Vehiculo(marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    companion object {
        var conducionBritanica:Boolean = true
        fun cambiarCondicionBritania(nuevaCondicion: Boolean) {
            conducionBritanica = nuevaCondicion
        }
    }

    override fun calcularAutonomia(): Float {
        return if(!esElectrico) super.calcularAutonomia()
        else {
            KM_L = 15.0f
            (this.combustibleActual * KM_L).redondear()
        }
    }

    fun realizaDerrape():Float {
        println("El automovil ha realizado un derrape.")
        if (esElectrico) this.combustibleActual -= 0.33f
        else this.combustibleActual -= 0.5f
        return this.combustibleActual.redondear()
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