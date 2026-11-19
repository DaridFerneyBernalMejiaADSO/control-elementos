# 💻 Control de Elementos

Sistema de gestión y control de entrada y salida de elementos tecnológicos, desarrollado para fortalecer la seguridad, organización y trazabilidad de los recursos del Centro Industrial de Mantenimiento Integral (CIMI).

## 📌 Descripción del proyecto

**Control de Elementos** es una aplicación de escritorio diseñada para digitalizar y optimizar el proceso de registro de usuarios y elementos tecnológicos que ingresan y salen de las instalaciones del CIMI.

El sistema busca reemplazar los registros manuales en papel por una solución tecnológica que permita administrar la información de manera organizada, reducir el riesgo de pérdida de información y facilitar el seguimiento de los elementos.

## 🎯 Objetivo general

Modernizar y optimizar el proceso de control de elementos tecnológicos mediante una aplicación digital que permita mejorar la seguridad, prevenir pérdidas y gestionar eficientemente los recursos.

## ⚙️ Funcionalidades principales

- **Inicio de sesión:** autenticación para el acceso del administrador.
- **Gestión de usuarios:** registro, consulta y actualización de información personal.
- **Gestión de elementos:** registro de elementos tecnológicos asociados a cada usuario.
- **Control de estados:** activar y desactivar elementos según su estado de ingreso o salida.
- **Actualización de información:** modificación de datos de usuarios y elementos.
- **Búsqueda y filtrado:** consulta de registros mediante identificación y filtros.
- **Generación de reportes:** exportación de información a archivos Excel.
- **Cierre de sesión:** finalización segura de la sesión del usuario.

## 🛠️ Tecnologías utilizadas

| Tecnología          | Aplicación                                       |
| ------------------- | ------------------------------------------------ |
| Java                | Lenguaje de programación                         |
| Apache NetBeans IDE | Entorno de desarrollo                            |
| MySQL               | Sistema de gestión de bases de datos             |
| JDBC                | Conexión entre Java y MySQL                      |
| Java Swing          | Desarrollo de interfaces gráficas                |
| Git y GitHub        | Control de versiones y almacenamiento del código |

## 🏗️ Estructura del proyecto

```text
control-elementos/
│
├── nbproject/
│
├── src/
│   ├── DataBase/
│   ├── Images/
│   ├── controller/
│   ├── model/
│   └── view/
│
├── Documentacion/
│   ├── Presentacion.pdf
│   ├── Presentacion.pptx
│   └── Informe.docx
│
├── .gitignore
├── README.md
├── build.xml
└── manifest.mf
```

## 🚀 Instalación y ejecución

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/DaridFerneyBernalMejiaADSO/control-elementos.git
   ```

2. Abrir Apache NetBeans IDE.

3. Seleccionar **File → Open Project**.

4. Buscar y abrir la carpeta `control-elementos`.

5. Configurar el servicio MySQL y la base de datos correspondiente.

6. Verificar la configuración de conexión JDBC en el proyecto.

7. Ejecutar la aplicación desde NetBeans.

## 📂 Documentación del proyecto

En la carpeta `Documentacion` se encuentran los documentos de apoyo y presentación:

- Presentación del proyecto en PDF.
- Presentación editable en PowerPoint.
- Informe del proyecto en Word.

## 👨‍💻 Autor

**Darid Ferney Bernal Mejia**

- Programa: Análisis y Desarrollo de Software.
- Ficha: 2500845.
- Institución: SENA.
- Centro: Centro Industrial de Mantenimiento Integral (CIMI).

## 📄 Licencia

Proyecto académico desarrollado con fines educativos.

