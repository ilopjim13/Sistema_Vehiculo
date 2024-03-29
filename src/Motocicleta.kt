import kotlin.math.absoluteValue

/**
 * Clase derivada Motocicleta
 * @param marca :String marca del vehiculo
 * @param modelo :String modelo del vehiculo
 * @param capacidadCombustible :Float cantidad máxima del combustible que tiene el vehiculo
 * @param combustibleActual :Float cantidad de combustible actual que tiene el vehiculo
 * @param kilometrosActuales :Float kilometros actuales que ha recorrido el vehiculo
 * @property cilindrada :Int indica el numero de cilendrada de la motocicleta
 */
class Motocicleta(nombre:String,
                  marca:String,
                  modelo:String,
                  capacidadCombustible:Float,
                  combustibleActual:Float,
                  kilometrosActuales:Float,
                  val cilindrada :Int
) :Vehiculo(nombre,marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {


    init {
        require(this.cilindrada in 125..CILINDRADAMAX) {"La cilindrada no puede ser menor a 125 ni mayor a 1000"}
    }

    companion object {
        const val CILINDRADAMAX = 1000
        const val KM_L_M = 20F
    }

    /**
     * Calcula la autonomia de la motocicleta con el combustible actual teniendo en cuenta la cilindrada, siendo 1000 20Km/L
     * y restando su valor entre 1000 al tope de los kilometros por litro.
     * @return Float retorna el calculo de la autonomia
     */
    override fun calcularAutonomia() : Float {
        return if(cilindrada == CILINDRADAMAX) super.calcularAutonomia()
        else (((CILINDRADAMAX - cilindrada) / CILINDRADAMAX) - KM_L_M * combustibleActual).absoluteValue.redondear()

    }


    /**
     * Realiza un viaje dependiendo de la distancia introducida y retornará la distancia restante
     * @return Float retorna la distancia restante para llegar a la distancia introducida
     */
    override fun realizarViaje(distancia:Float) :Float {
        val distanciaRecorrida = this.calcularAutonomia()
        val distanciaRestante:Float
        if (distanciaRecorrida < distancia) {
            distanciaRestante = distancia - distanciaRecorrida
            this.combustibleActual = 0.0f
            this.kilometrosActuales += distanciaRecorrida.redondear()

        }
        else {
            distanciaRestante = 0.0f
            this.combustibleActual -= (distancia / KM_L_M).redondear()
            this.kilometrosActuales = this.kilometrosActuales.redondear() + distancia.redondear()
        }
        return distanciaRestante.redondear()
    }


    /**
     * Realiza un caballito consumiendo combustible y retorna la cantidad de combustible actual que le queda
     * @return Float el combustible actual despues del caballito
     */
    fun realizoCaballito() : Float {
        combustibleActual -= (6.5f / KM_L_M).redondear()
        this.combustibleActual = this.combustibleActual.redondear()
        return combustibleActual.redondear()
    }

    /**
     * Muestra la información de las motocicletas
     * @return String retorna la información correspondiente
     */
    override fun toString(): String {
        return "${super.toString()}, cilindrada: $cilindrada"
    }

}