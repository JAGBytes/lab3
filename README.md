<h1>laboratorio 3</h1>

### Jorge Andrés Gamboa Sierra
### Andrea Camila Torres Gonzales

<h2>Objetivos</h2>
<ol>
    <li>Como hacer pruebas unitarias.</li>
    <li>Utilizar anotaciones @Test del framework JUnit</li>
    <li>Aplicar TDD.</li>
</ol>
<h2>Introducción</h2>
Las pruebas unitarias son una parte esencial en el desarrollo de software, ya que permiten validar la lógica de cada método y contribuir a la creación de un código sólido y robusto. Durante este laboratorio, se utilizará Maven, 
una herramienta muy útil para gestionar dependencias como JUnit, que es fundamental para la ejecución de las pruebas unitarias.
Trabajaremos con una aplicación basada en una biblioteca, que incluye libros, préstamos y usuarios. Implementaremos los métodos correspondientes en la clase Library aplicando la metodología de desarrollo guiado por pruebas (TDD). 
Esta práctica nos ayudará a escribir código enfocado en las pruebas, asegurando así su calidad y fiabilidad.
<h2>Marco Teórico</h2>

<h2>Procedimiento</h2>
<h3>CREAR PROYECTO CON MAVEN</h3>
Para crear el proyecto en maven, nos apoyamos en el arquetipo (archetypes)
```
mvn archetype:generate -DgroupId=edu.eci.cvds -DartifactId=Library -Dpackage=edu.eci.cvds.tdd -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
<h3>AGREGAR DEPENDENCIA JUNIT5</h3>
Para agregar la dependencia, nos vamos al archivo pom.xml y colocamos la siguiente dependencia
```
<dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>5.6.0</version>
      <scope>test</scope>
</dependency>
```
Así mismo, cambiamos la versión java. Cambiamos <maven.compiler.target> a la versión 1.8
```
<properties>
    <maven.compiler.target>1.8</maven.compiler.target>
    <maven.compiler.source>1.8</maven.compiler.source>
 </properties>
```
<h3>AGREGAR CLASES</h3>
<h2>PRUEBAS UNITARIAS Y TDD</h2>
<h3>CREAR CLASE DE PRUEBA</h3>
<h3>COBERTURA</h3>
<h3>SONARQUBE</h3>
<h3>IMPLEMENTACIÓN DE LOS MÉTODOS</h3>
<h2>Conclusiones</h2>
<h2>Bibliografía</h2>