# Patrones de Software

## Abstract Factory
TerminalFactory crea los tres productos relacionados que requiere una terminal: equipo de carga, validador de estabilidad y documento de embarque. Esto mantiene la coherencia de cada familia de terminales sin necesidad de estructuras condicionales complejas.

Factory Method
ManifestRecorder define el flujo de procesamiento y delega la creación de unidades en createUnit(). Las tres clases de registro analizan su propio formato de manifiesto y calculan el peso según las reglas específicas de cada caso.

Builder
StowagePlan.Builder crea el objeto inmutable StowagePlan al tiempo que valida todos los campos obligatorios. Asimismo, facilita la incorporación de datos opcionales del plan sin requerir un constructor con una lista extensa de parámetros.

Estos son los tres patrones requeridos que se utilizan en la solución.
