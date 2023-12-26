

* PARTE 1 IMPLEMENTACION BASICA

1) configurar conexion a mysql en application.properties

2) crear un paquete llamado auth y dentro crear un Controller(AuthController) con dos metodos
   basicos para hacer una prueba

3) crear un paquete llamado demo y dentro crear un Controller(DemoController) que viene a representar
   toda la estructura del CRUD que vamos a trabajar en nuestra APP.

4) crear un paquete llamado config y dentro crear la clase SecurityConfig en donde hay una configuracion
   de seguridad muy basica.

En este punto se puede realizar una prueba basica en donde los endpoints que no tienen restriccion
(url localhost:8080/auth/login y url localhost:8080/auth/register)
se pueden acceder (AuthController) y los endpoints que tienen restriccion (url localhost:8080/demo/controller) 
no tienen acceso (DemoController). Esto confirma que la implementacion de Spring Security está bien.

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

En este punto podemos realizar otra prueba en la url localhost:8080/auth/register.Aca podemos crear un 
registro y comprobar en la base de datos si todo ha ocurrido como se esperaba.Si todo está bien, 
el registro nos devuelve un token.

	{
		"username" : "luis123@live.com",
		"password" : "1234",
		"firstName" : "Luis",
		"lastname" : "Saa",
		"country" : "Colombia"
	}

Entre la primera prueba y esta segunda prueba hemos modificado dos clases que son
AuthController y SecurityConfig.Ademas hemos creado 10 clases e interfaces.

* ----------------------------------------------------------------------------------------------
* ----------------------------------------------------------------------------------------------

* PARTE 3, CONFIGURANDO LOGIN

18) [modificar] la class AuthService.Implementar la logica del metodo login.

En este punto hacemos una nueva prueba de login en donde en la url localhost:8080/auth/login, pasamos en el cuerpo
de la peticion el username y el password. si todo funciona bien nos devuelve el token. sino nos devuelve un codigo
de acceso prohibido 403.

	{
		"username" : "luis123@live.com",
		"password" : "1234"
	}

19) [modificar] la class JwtService creando los metodos :

	//2)
	public String getUsernameFromToken(String token) {

		return getClaim(token, Claims::getSubject);
	}

	//6)
	public boolean isTokenvalid(String token, UserDetails userDetails) {

	    final String username = getUsernameFromToken(token);
	    return (username.equals(userDetails.getUsername()) && isTokenExpired(token)); //devuelve true o false
	}

	//1) creamos este metodo primero
	//se llama desde getUsernameFromToken(String token) y getExpiration(String token)
	public <T> T getClaim(String token, Function<Claims, T> claimsResolver){

	    final Claims claims = getAllClaims(token);
	    return claimsResolver.apply(claims);
	}

	//metodos para usar con isTokenValid()
	//metodo para usar con getClaim():
	//4)
	private Claims getAllClaims(String token ){

	    return Jwts
	            .parserBuilder()
	            .setSigningKey(getKey())
	            .build()
	            .parseClaimsJwt(token)
	            .getBody();
	}

    //3)
    private Date getExpiration(String token){

        return getClaim(token, Claims::getExpiration);
    }

    //metodo que sera llamado desde isTokenValid()
    //5)
    private boolean isTokenExpired(String token){

        return getExpiration(token).before(new Date());
    }

19) [modificar] el metodo doFilterInternal() dentro de la class JwtAuthenticationFilter que se 
    encuentra dentro del paquete jwt.

En este punto hacemos una prueba final.

//PRUEBA FALLIDA .DEVOLVERME EN EL VIDEO(56:50) HASTA LA PRUEBA ANTERIOR QUE SI SALIO BIEN Y VERIFICAR TODO.
//SINO COMPARAR CLASE POR CLASE CON CODIGO DE GITHUB.
