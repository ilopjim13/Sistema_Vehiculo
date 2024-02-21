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
class Motocicleta(marca:String,
                  modelo:String,
                  capacidadCombustible:Float,
                  combustibleActual:Float,
                  kilometrosActuales:Float,
                  val cilindrada :Int
) :Vehiculo(marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {


    init {
        require(this.cilindrada in 125..1000) {"La cilindrada no puede ser menor a 125 ni mayor a 1000"}
        KM_L = 20.0f
    }

    companion object {
        val CILINDRADAMAX = 1000f
    }

    /**
     * Calcula la autonomia de la motocicleta con el combustible actual teniendo en cuenta la cilindrada, siendo 1000 20Km/L
     * y restando su valor entre 1000 al tope de los kilometros por litro.
     * @return Float retorna el calculo de la autonomia
     */
    override fun calcularAutonomia() : Float {
        return if(cilindrada == 1000) super.calcularAutonomia()
        else {
            KM_L = (((CILINDRADAMAX - cilindrada) / CILINDRADAMAX) - 20.0f).absoluteValue
            (combustibleActual * KM_L).redondear()
        }
    }

    /**
     * Realiza un caballito consumiendo combustible y retorna la cantidad de combustible actual que le queda
     * @return Float el combustible actual despues del caballito
     */
    fun realizoCaballito() : Float {
        println("La motocicleta ha realizado un caballito.")
        combustibleActual -= (6.5f / KM_L).redondear()
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