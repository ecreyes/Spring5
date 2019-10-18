# Documentación Spring

1. [Introducción](#TOC-introduccion)
2. [Vista](#TOC-vista)
   * [Mostrar variables](#TOC-variable)
   * [If thymeleaf](#TOC-if)
   * [For thymeleaf](#TOC-for)
   * [Href thymeleaf](#TOC-href)
   * [Recursos estaticos css/js/img thymeleaf](#TOC-static)
3. [Controlador](#TOC-controlador)
   * [Renderizar vista](#TOC-con-vista)
   * [Variables para la vista](#TOC-con-var)
   * [Rutas primer nivel](#TOC-con-rutas)
   * [Recibir parámetros por url](#TOC-con-params)
   * [Path variable](#TOC-con-path)

## <a name="TOC-introduccion"></a>Introducción
Esta guía esta hecha con el fin de obtener tips o conceptos de forma rápida y asi implementar código en cualquier proyecto de Spring.

## <a name="TOC-vista"></a>Vista
Las vistas en Spring boot son manejadas con `thymeleaf` y estan ubicadas en `src/main/resources/templates`, dentro de este directorio
se pueden crear archivos html y carpetas que contengan archivos html. Ahora si se desea crear un archivo html debe tener este formato
que es el básico:
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>titulo</title>
</head>
<body>
<h1>Welcome</h1>
</body>
</html>
```
Notar el `xmlns:th="http://www.thymeleaf.org"` este nos permitirá utilizar todo lo que nos ofrece thymeleaf al agregar atributos `th`
al html.

### <a name="TOC-variable"></a>Mostrar variables
Supongamos que desde el controlador se quiere  enviar una variable String que contiene el titulo del sitio a la vista, para mostrar
esta variable en la vista se debe agregar lo siguiente:
```html
<title th:text="${titulo}"></title>
```
Lo que interesa es `th:text="${titulo}"`, esto permite renderizar cualquier tipo de variable enviada desde el controlador a la vista,
la variable en el controlador se debe llamar `titulo` y debe venir de un `model.addAttribute("titulo","Inicio");` ubicado en el controlador.
### <a name="TOC-if"></a>If thymeleaf
Supongamos que se envia desde el controlador un usuario y este puede o no tener correo,para mostrar el correo en la vista se hace
lo siguiente:
```html
<p>Correo: <span th:if="${usuario.email}" th:text="${usuario.email}"></span></p>
<span th:if="!${usuario.email}">Este usuario no posee email.</span>
```
Si existe el correo del usuario mostrará el correo en el campo, si no existe el correo mostrará un mensaje.
### <a name="TOC-for"></a>For thymeleaf
Suponiendo que desde el controlador se envia un listado de usuarios y quieren mostrarse en la vista, resulta el siguiente
código:
```html
<div th:if="${usuarios.size()==0}">
    <p>No hay usuarios en el sistema</p>
</div>

<div th:if="${usuarios.size()>0}">
<table>
    <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellido</th>
        </tr>
    </thead>
    <tbody>
        <tr th:each="usuario: ${usuarios}">
            <td th:text="${usuario.nombre}"></td>
            <td th:text="${usuario.apellido}"></td>
        </tr>
    </tbody>
</table>
</div>
```
En este caso la variable `usuario` va a recorrer la lista de `usuarios`, entonces de esta forma se puede acceder a cada elemento.

### <a name="TOC-href"></a>Href thymeleaf
Si sabemos que la ruta a cierta página es `http://localhost:8080/app/index` y queremos acceder a esa vista mediante un href, con
thymeleaf se hace de la siguiente forma:
```html
<a th:href="@{/app/index}">ir al inicio del sitio</a>
```
Ahora si se quiere acceder a una página que tiene un parámetro en la url como esta `http://localhost:8080/params/string?texto=holamundo`
donde recibe una variable opcional texto, en el html se representa de la siguiente forma:
```html
<a th:href="@{/params/string(texto='hola mundo!')}"> enviar hola mundo por get</a>
```
donde `(texto='hola mundo!')` es el parámetro opcional.
### <a name="TOC-static"></a>Recursos estaticos css/js/img thymeleaf
Primero se debe crear una carpeta `static` ubicada en resources, quedando de esta forma `src/main/resources/static`, una vez creada
esta carpeta se podran colocar recursos estaticos para ser renderizados en una vista, como por ejemplo css:
```html
<link rel="stylesheet" type="text/css" th:href="@{/css/mystyle.css}">
```
o ingresar una imagen a la vista:
```html
<img th:src="@{/img/spring-framework.png}" alt="Logo de spring">
```
## <a name="TOC-controlador"></a>Controlador
Los controladores son usados para recibir las peticiones del cliente y asi realizar las peticiones ya sea solicitando datos a los modelos o mostrando la vista respectiva.

Cuando uno crea una aplicación de spring siempre se encuentra este archivo `SpringappApplication.java`, en la ubicación de ese archivo
hay que crear un package en el mismo nivel que se llame `controller` y dentro de esta carpeta se crearan todos los archivos controller
con el nombre `NombreController` donde `Nombre` es el nombre con el que se llamará el controlador.

```java
@Controller
public class IndexController {

}
```
Para que la clase creada sea de tipo controller hay que usar el decorador `@Controller`.
### <a name="TOC-con-vista"></a>Renderizar vista
Ahora supongamos que al acceder a la ruta `/index` se desea mostrar la vista index creada con thymeleaf, para esto hay que colocar en
el controlador:
```java
@Controller
public class IndexController {

  @GetMapping(value="/index")
  public String index(){
      return "index";
  }

}
```
El `@GetMapping` es un decorador para procesar peticiones http a la ruta asignada en value, en este caso seria `http://localhost:8080/index`,y lo que retorna es el nombre de la vista `index`, asi se debe llamar el archivo html `index.html`
### <a name="TOC-con-var"></a>Variables para la vista
Suponiendo que el controlador recibe una variable de la logica de negocio (modelo) y desea mostrar esta variable en la vista
se debe agregar lo siguiente al método del controlador que maneja la ruta:
```java
public String index(Model model){
   model.addAttribute("titulo","Inicio");
   model.addAttribute("welcome","Bienvenido al sitio");
   return "index";	      
}
```
Al utilizar `Model` como parámetro en el método, este permite usar su propiedad `model.addAttribute` que permite agregar un atributo,
donde el primer parámetro es la clave y el segundo es el valor, notese que el segundo valor no necesariamente puede ser un String o
un integer, tambien puede ser algo mas complejo como un List.
### <a name="TOC-con-rutas"></a>Rutas primer nivel.
Las rutas de primer nivel como el nombre lo dice, son las rutas que aparecen primero en la url, por ejemplo si queremos que el controlador maneje todas las rutas que empiecen por `app`, la url queda asi `http://localhost:8080/app`
```java
@Controller
@RequestMapping("/app")
public class IndexController {

}
```
Entonces ahora agregando el `@RequestMapping("/app")` este controlador se encargará de procesar todas las peticiones a esa url, y en
caso de que exista un método como por ejemplo esto:
```java
@Controller
@RequestMapping("/app")
public class IndexController {

  @GetMapping(value="/index")
  public String index(){
      return "index";
  }

}
```
El método index ahora es procesado por esta url `http://localhost:8080/app/index`
### <a name="TOC-con-params"></a>Recibir parámetros por url.
Suponiendo que existe este controlador:
```java
@Controller
@RequestMapping("/params") //Se crea una ruta de primer nivel.
public class ParamController {

    /**  Esta ruta se encarga de recibir parámetros del tipo GET, es decir recibe un parametro
     *   http://localhost:8080/params/string?texto=holamundo y luego se muestra en la vista.
     *   el value es el nombre que se usará en el get, por defecto es obligatorio el parametro asi que se desactiva,
     *   y en caso de que no se ingrese hay un valor por defecto
     * **/
    @GetMapping("/string")
    public String param(@RequestParam(value = "texto", required = false, defaultValue = "algun valor") String texto,
                        Model model) {
        model.addAttribute("texto", texto);
        return "params/ver";
    }
}
```
El `@RequestParam` es el encargado de recibir el parámetro por la url en este caso se declara con `@RequestParam(value = "texto", required = false, defaultValue = "algun valor") String texto`, el dato se almacenará como String en la variable texto, y lo que
esta dentro de () son las opciones que se usarán para recibir el parámetro.

`Nota:`el return `params/ver` esta redireccionando una vista `ver.html` que se encuentra dentro de una carpeta llamada `params`, todo esto dentro de resources.
### <a name="TOC-con-path"></a>Path variable.
Suponiendo que se desea recibir un parametro que va variando en la url, como por ejemplo el típico id al hacer un show,
para generar la ruta se hace algo de este estilo:
```java
  @GetMapping("/ver/{id}")
  public String ver(@PathVariable int id, Model model){
      model.addAttribute("id",id);
      return "variable/ver";
  }
```
Se coloca entre corchetes y se recibe el valor de id con `@PathVariable int id`.
