## Descripción
Un microservicio construido con Spring Boot y Gradle con la funcionalidad de registrar un usuario e iniciar sesión.

- Documentación con Swagger Editor dentro de la carpeta Postman
- Uso de base de datos en memoria h2
- Generación de token con JWT
- Encriptación de password

El microservicio contiene 2 endpoint, para crear un usuario en base al contrato de entrada y su login mediante token.
#### Sing Up
- sobre el contrato de entrada se le aplica inmediatamente en el controller la validación de los campos recibidos en el JSON, se valida que el correo corresponda a un formato de tipo email
- El password debe de cumplir con las siguientes reglas: 
    - contener entre 8 y 12 caracteres
    - solo una mayuscula
    - solo 2 digitos, no necesariamente consecutivos
- Contrato de salida específica datos de creación del usuario, id de la persistencia, fecha de creación, estado activo, última vez que hizo login y su token.
#### Login
- Sobre el contrato de entrada se recibe un JSON con email, password y el token.
- La validación del token se realiza en la capa de servicio para ocultar lógica del negocio, mediante el provider del JWT.

## ¿Cómo ejecutar la aplicación?

La aplicación usa Gradle para la compilación y arranque del proyecto. 

Una vez clonado el proyecto ejecutar los siguientes comandos para levantar la aplicación

```(shell)
gradlew build
java -jar ./build/libs/evaluacion-0.0.1-SNAPSHOT.JAR
```
si no también ejecutando solo este comando 
```(shell)
gradlew bootRun
```

Una vez realizado el arranque de la aplicación dirigirse al Postman para realizar las pruebas e importar la colección de Postaman que se encuentra en la carpeta Postman.
