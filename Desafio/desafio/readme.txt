Pasos para la ejecución y prueba del Servicio de Creación de Usuarios.

1.- Tener instalado SpringBoot en su version 3.3.3
2.- Tener instalado el JDK 17
3.- Abrir una terminal y ubicarse al interior del directorio Desafio/desafio/
    (A la misma altura donde se encuentra este readme.txt).
4.- Ejecutar el comando mvn:
        clean install
5.- Ejecutar el comando mvn: 
        clean compile package
6.- Finalmente ejecutar el comando:
        mvn spring-boot:run
    Este último comando permitirá que se ejecute el servicio.
7.- Tener instalado PostMan o alguna otra herramienta que le permita
    realizar las pruebas sobre los servicio. En la herramienta se debe
    indicar la siguiente url:
        http://localhost:8080/api/Usuario/create
    La metodo de la solicitud debe ser POST y debe tener en el body un
    json con la siguiente estructura:
        {
          "name": "Nombre del Usuario",
          "email": "usaurio@gmail.com",
          "password": "AA11##bbPass",
          "phones":
          [
            {
              "number": "98765432",
              "citycode": "9",
              "contrycode": "+56"
            },
            {
              "number": "12345678",
              "citycode": "9",
              "contrycode": "+56"
            }
          ]
        }
    Es importante mensionar que este json puede llevar o no llevar telefonos para
    un nuevo usuario.
8.- Si necesita revisar los resultados en la base de datos H2 puede acceder a 
    la url:
      http://localhost:8080/h2-console/
    Utilizando los siguiente parametros:
      Saved Settings: Generic H2 (Embedded)
      Setting Name: Generic H2 (Embedded)
      Driver Class: org.h2.Driver
      JDBC URL: jdbc:h2:~/h2_data/testdb
      User Name: sa
      Password:
    La password debe ir vacía, y después de verificar esos datos 
    se debe presionar el boton Connect.
9.- Para acceder a Swagger a revisar los servicios, debe ingresar a la siguiente url:
      http://localhost:8080/swagger-ui.html
10.-  Para ejecutar las pruebas unitarias con JUnit, se debe ubicar en el directorio Desafio/desafio/
      y ejecutar el siguiente comando:
        mnv test