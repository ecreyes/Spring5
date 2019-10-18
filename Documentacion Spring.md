# Documentación Spring

1. [Introducción](#TOC-introduccion)
2. [Vista](#TOC-vista)
   * [Mostrar variables](#TOC-variable)
   * [If thymeleaf](#TOC-if)
   * [For thymeleaf](#TOC-for)
   * [Href thymeleaf](#TOC-href)
   * [Recursos estaticos css/js/img thymeleaf](#TOC-static)

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
