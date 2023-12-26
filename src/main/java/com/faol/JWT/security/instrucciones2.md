

* PARTE 1 IMPLEMENTACION BASICA

1) configurar conexion a mysql en application.properties

2) crear un paquete llamado auth y dentro crear un Controller(AuthController) con dos metodos
   basicos para hacer una prueba

3) crear un paquete llamado demo y dentro crear un Controller(DemoController) que viene a representar
   toda la estructura del CRUD que vamos a trabajar en nuestra APP.

4) crear un paquete llamado config y dentro crear la clase SecurityConfig en donde hay una configuracion
   de seguridad muy basica.

En este punto se puede realizar una prueba basica en donde los endpoints que no tienen restriccion
se pueden acceder (AuthController) y los endpoints que tienen restriccion no tienen acceso
(DemoController). Esto confirma que la implementacion de Spring Security está bien.

* ----------------------------------------------------------------------------------------------
* ----------------------------------------------------------------------------------------------

* PARTE 2 IMPLEMENTACION AVANZADA

5) crear un paquete llamado jwt y dentro crear la clase JwtAuthenticationFilter

6) crear dentro del paquete auth una clase llamada LoginRequest que contiene username y password.

7) crear dentro del paquete auth la clase RegisterRequest que contiene los demas atributos que se 
   quieran agregar a user.

8) crear dentro del paquete auth la clase AuthRegister que contiene el String Token.

En estas 3 clases ultimas clases creadas se implementan las anotaciones de lombok 
@Data, @AllArgsConstructor, @NoArgsConstructor y @Builder.

9) crear un paquete llamado user y dentro crear la enum class Role con dos atributos (ADMIN, USER)

10) crear dentro del paquete user la clase Entity llamada User que contiene los mismos atributos de la 
   clase RegisterRequest mas user_id y role(total 7 atributos).Esta clase implementa UserDetails
   lo que conlleva a implemetntar 5 metodos.Poner los ultimos 4 en true.

11) dentro del paquete user crear la interface UserRepository

12) en pom.xml agregamos las dependencias de JWT:

    <!--JWT-->
	<dependency>
		<groupId>io.jsonwebtoken</groupId>
		<artifactId>jjwt-api</artifactId>
		<version>0.11.5</version>
	</dependency>

	<dependency>
		<groupId>io.jsonwebtoken</groupId>
		<artifactId>jjwt-jackson</artifactId>
		<version>0.11.5</version>
	</dependency>

	<dependency>
		<groupId>io.jsonwebtoken</groupId>
		<artifactId>jjwt-impl</artifactId>
		<version>0.11.5</version>
	</dependency>

13) dentro del paquete jwt crear la clase JwtService

14) crear en el paquete auth la clase AuthService

15) [modificar] en el AuthController las respuestas de los metodos.En vez de String van a 
   retornar ResponseEntity<AuthResponse> tambien implementamos el AuthService recien creado

16) dentro del paquete config crear la clase ApplicationConfig

17) [modificar] la class SecurityConfig que está dentro del paquete config.

En este punto podemos realizar otra prueba.Aca podemos crear un registro y comprobar en la 
base de datos si todo ha ocurrido como se esperaba.Si todo está bien, el registro nos devuelve
un token.

Entre la primera prueba y esta segunda prueba hemos modificado dos clases que son
AuthController y SecurityConfig.Ademas hemos creado 10 clases e interfaces.
