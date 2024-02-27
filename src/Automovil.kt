/**
 * Clase derivada Automovil
 * @param marca :String marca del vehiculo
 * @param modelo :String modelo del vehiculo
 * @param capacidadCombustible :Float cantidad máxima del combustible que tiene el vehiculo
 * @param combustibleActual :Float cantidad de combustible actual que tiene el vehiculo
 * @param kilometrosActuales :Float kilometros actuales que ha recorrido el vehiculo
 * @property esHibrido :Boolean indica si el coche es hibrido o no
 */
class Automovil(nombre:String,
                marca:String,
                modelo:String,
                capacidadCombustible:Float,
                combustibleActual:Float,
                kilometrosActuales:Float,
                var esHibrido :Boolean
) :Vehiculo(nombre,marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    companion object {
        const val KM_L_HIBRIDO = 15
        const val KM_L_HIBRIDO_MAS10 = 25
        const val KM_L_HIBRIDO_MENOS5 = 10
        var conducionBritanica:Boolean = true
        fun cambiarCondicionBritania(nuevaCondicion: Boolean) {
            conducionBritanica = nuevaCondicion
        }
    }

    override fun calcularAutonomia(): Float {
        return if (!esHibrido) super.calcularAutonomia()
        else {
            when (premio) {
                Premios.SUMAR10 -> (combustibleActual * KM_L_HIBRIDO_MAS10).redondear()
                Premios.RESTAR5 -> (combustibleActual * KM_L_HIBRIDO_MENOS5).redondear()
                else -> (combustibleActual * KM_L).redondear()
            }
        }
    }


    /**
     * Realiza un viaje dependiendo de la distancia introducida y retornará la distancia restante
     * @return Float retorna la distancia restante para llegar a la distancia introducida
     */
    override fun realizarViaje(distancia:Float) :Float {
        if (!esHibrido) return super.realizarViaje(distancia)
        else {
            val distanciaRecorrida = this.calcularAutonomia()
            val distanciaRestante: Float
            if (distanciaRecorrida < distancia) {
                distanciaRestante = distancia - distanciaRecorrida
                this.combustibleActual = 0.0f
                this.kilometrosActuales += distanciaRecorrida.redondear()

            } else {
                when(premio) {
                    Premios.SUMAR10 -> {
                        distanciaRestante = 0.0f
                        this.combustibleActual -= (distancia / KM_L_HIBRIDO_MAS10).redondear()
                        this.kilometrosActuales +=  distancia.redondear()}
                    Premios.RESTAR5 -> {
                        distanciaRestante = 0.0f
                        this.combustibleActual -= (distancia / KM_L_HIBRIDO_MENOS5).redondear()
                        this.kilometrosActuales +=  distancia.redondear() }
                    else -> {
                        distanciaRestante = 0.0f
                        this.combustibleActual -= (distancia / KM_L_HIBRIDO).redondear()
                        this.kilometrosActuales +=  distancia.redondear()
                    }
                }
            }
            return distanciaRestante.redondear()
        }
    }


    /**
     * Realiza un derrape consumiendo combustible y retorna la cantidad de combustible actual que le queda
     * @return Float el combustible actual despues del derrape
     */
    fun realizaDerrape():Float {
        if (esHibrido) this.combustibleActual -= (6.25f / KM_L_HIBRIDO).redondear()
        else this.combustibleActual -= (7.5f / KM_L).redondear()
        this.combustibleActual = this.combustibleActual.redondear()
        return this.combustibleActual.redondear()
    }

    /**
     * Muestra la información de los automóviles
     * @return String retorna la información correspondiente
     */
    override fun toString(): String {
        return "${super.toString()}, ${
            if (esHibrido) "es electrico"
            else "no es electrico"
        }, y la condución ${
            if (conducionBritanica) "es Británica"
            else "no es Británica"
        }."
    }
}