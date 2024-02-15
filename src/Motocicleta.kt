class Motocicleta(marca:String,
                  modelo:String,
                  capacidadCombustible:Float,
                  combustibleActual:Float,
                  kilometrosActuales:Int,
                  val cilindrada :Int
) :Vehiculo(marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

}