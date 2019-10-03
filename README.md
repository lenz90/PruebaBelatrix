# Prueba de Belatrix

## Observaciones:
1. Se tiene mucha lógica de distintos dominios.
2. Se tiene una conexión a cualquiera, teniendo una instancia por cada llamada. Lo cual no está tan mal, pero para esta ocasión se puede usar una sola instancia y solo se llama cuando se le da para guardar en base de datos.

## Cómo resolví el problema:
- Se creó una clase Conexión la cual solo devuelve una instancia que ya se generó alguna vez y toda la lógica de base de datos se abstrae en dicha clase.

- Se creó una clase Mensaje y se usó validaciones en el constructor asi se evita crear instancias sin validar.

- La clase Mensaje abstrae toda la lógica que le pertenece al mensaje en sí.

- Se crea un Enum de tipo mensaje ya que se debe limitar qué tipo de mensaje se quiere mandar.

- Se crea una clase para el manejo de constantes.

- Se usó el concepto name-constructor ya que las clases deben tener una razón lógica para crearse.

- Se utilizó el principio de única responsabilidad, desacoplando bastante la lógica y dejándola solo para el que le pertenece.

- Para la Clase JobLogger, se válido de la misma en el constructor ya que es preferible validarlo lo mas cohesionado posible.

## Cómo se pudo resolver mejor la solución:
- Usando lombok.
- Ordenándolos por paquetes.
- Usando reactor de Spring.
- Mockito para las pruebas.

## Test:
No pude realizarlo ya que tenía algunos temas que no me dió el tiempo, disculpen, claro que lo realizaré pero lo subiré luego.

Gracias.
