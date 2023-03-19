## Descripción

Proyecto que genera un archivo en formato txt o csv con los proveedores de un cliente dado a partir de los datos almacenados en una base de datos MySQL.
Para ello se ha creado un proyecto java al cual se le pasa el código del cliente y opcionalmente el tipo de fichero a generar, y al ejecutarlo se creará un directorio 'exportedFiles' donde se guardarán los fichero con formato: <yyyyMMdd_HHmmss>_proveedores_<codio cliente>.<txt/csv> 

### Librerías utilizadas
  
**Versión de java 11**

## Instalación

### Base de datos (docker)

La base de dato se ha creado en un contenedor docker que se encuentra subido al proyecto, carpeta 'docker'.
Para lanzar la máquina es necesario tener instalado en el sistema 'docker' y 'docker-compose'.
en el directorio 'docker' lanzar la siguiente sentencia:

```bash
docker-compose up -d
```

### Ejecutable

La última versión del ejecutable se encuentra en el directorio raiz llamado 'generateSuppliers-0.0.1-SNAPSHOT.jar'. Para lanzarla es necesario tener instalada **la versión 11 de java** y lanzar el ejecutable:
  
```bash
java -jar generateSuppliers-0.0.1-SNAPSHOT.jar <código cliente> <opcional: CSV>
```
Ejemplos:

1. Generar un fichero txt con los proveedores del cliente 5:
```bash
java -jar generateSuppliers-0.0.1-SNAPSHOT.jar 5
```
  
2. Generar un fichero CSV con los proveedores del cliente 6:
```bash
java -jar generateSuppliers-0.0.1-SNAPSHOT.jar 6 CSV
```

