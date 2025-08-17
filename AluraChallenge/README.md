 Conversor de Monedas – Alura Challenge

Este proyecto es un conversor de monedas hecho en Java 24 usando Maven en IntelliJ IDEA.  

---

 Funcionalidad

El programa permite convertir montos entre distintas monedas utilizando la API pública de [ExchangeRate-API](https://www.exchangerate-api.com/) 


 El programa se ejecuta en un bucle while hasta que el usuario elija 7 (Salir).  
Si se ingresa una opción inválida (ej: 0 o 10), se mostrará un mensaje de error y pedirá nuevamente la opción.  
También valida que el monto ingresado sea numérico y mayor o igual a 0.  

---

 Tecnologías utilizadas

- Java 24
- Maven (gestor de dependencias)
- Gson (para parsear JSON)
- HttpClient / HttpRequest / HttpResponse (para consumo de la API)
- IntelliJ IDEA como entorno de desarrollo

---

Ejemplo de uso de la api
https://v6.exchangerate-api.com/v6/TU_API_KEY/latest/USD

Por usos de seguridad personales por favor usar una clave ya que debo remover mi clave personal para evitar inconvenientes.



Ejemplo de ejecucion:

==============================
CONVERSOR DE MONEDAS (ALURA)
==============================
1) Dólar -> Peso Argentino
2) Peso Argentino -> Dólar
3) Dólar -> Real Brasilero
4) Real Brasilero -> Dólar
5) Dólar -> Peso Colombiano
6) Peso Colombiano -> Dólar
7) Salir

Elija una opción válida: 1
Ingrese el monto a convertir: 100

=> 100.00 USD = 100000.00 ARS
   Tasa utilizada: 1 USD = 1000.0000 ARS
