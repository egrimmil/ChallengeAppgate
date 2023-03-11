# **Challenge AppGate**


![](app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp)


***Tabla de contenido***

1. [Descripción](#descripción)
2. [Intalación y uso](#intalación-y-uso)
3. [Desarrollo](#desarrollo)

## **Descripción**

Esta app te permite registrar tu usuario a nuestro sistema de información. Al realizar login con tu usuario valida si fue correcto o no. Cuando es correcto te muestra la liste de intentos realizados con su fecha y su respectivo resultado.


## **Intalación y uso**

*Prerequisitos:*

- Android Studio (^4.2.1)
- Gradle (^4.2.0)
- Gradle Properties (^6.7.1)

*Herramientas:*

- SDK > 24
- CompileSdk 33
- Material 1.8.0
- Navigation 2.5.3
- Room 2.5.0


## **Desarrollo**

- Para implementar una libreria se debe colocar la verión en el archivo **version.gradle** ubicado en la carpeta buldsystem en la raiz del proyecto.
- Colocar las imagenes en SVGs


### *Arquitectura*

Esta app usa la [***Clean Architecture***](https://developer.android.com/topic/architecture?hl=es-419#recommended-app-arch) integrada con [***MVVM Android***](https://www.journaldev.com/20292/android-mvvm-design-pattern).

![](https://media.geeksforgeeks.org/wp-content/uploads/20201002215007/MVVMSchema.png)

### *Patrones de diseño*

- Creacionales
    - Factory method
    - Singleton
    - Builder

- Estructurales
    - Adapter
    - Facade
    - Proxy
    - Repository

- Comportamientos
    - Chain of resposability
    - Mediator
    - Observer
    - State
