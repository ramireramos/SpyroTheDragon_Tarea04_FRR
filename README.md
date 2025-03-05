# Spyro The Dragon - Guía Interactiva

## Introducción
Spyro The Dragon App es una aplicación diseñada para explorar el universo de Spyro, permitiendo a los usuarios descubrir personajes, mundos y coleccionables del juego clásico. Para la realización de este ejercicio, se ha implementado una **guía interactiva** que ofrece una introducción a las principales secciones de la app con una experiencia inmersiva.

## Características principales
- **Exploración de personajes**: Descubre y visualiza información sobre los diferentes personajes de Spyro.
- **Mundos y escenarios**: Navega por los distintos mundos del juego.
- **Coleccionables**: Consulta los objetos clave que puedes encontrar en el juego.
- **Guía interactiva**: Una guía en formato de diálogos superpuestos que te introduce en la funcionalidad de la app.
- **Easter Egg de fuego en Spyro**: Cuando mantenemos el dedo pulsado sobre el personaje "Spyro" en la sección de personajes durante 5 segundos, aparece animación simulando que escupe fuego por la boca.
- **Easter Egg con video**: Se activa tras cuatro toques consecutivos en las gemas dentro de la pestaña de coleccionables.

## Tecnologías utilizadas
- **Java**: Lenguaje principal de programación.
- **Android Studio**: Entorno de desarrollo.
- **Drawable**:Se han utilizado recursos gráficos y animaciones XML para transiciones y efectos visuales.
- **Animaciones**: Animaciones XML para transiciones y efectos visuales.
- **VectorAssets**: Se ha utilizado para importar SVG a XML.
- **Canvas**: Para la animación de fuego de Spyro.
- **SharedPreferences**: Para guardar el estado de la guía.
- **SoundPool**: Para los efectos de sonido al pasar las diferentes pantallas de la guía.
- **MediaPlayer**: Para la reproducción del video del Easter Egg.

## Instrucciones de uso
1. Clona el repositorio:
   ```sh
   git clone https://github.com/ramireramos/Tarea04_FRR.git
   ```
2. Abre el proyecto en **Android Studio**.
3. Asegúrate de tener configurado un dispositivo o emulador.
4. Ejecuta la aplicación y sigue la guía interactiva.

## La Guía Interactiva
- Al iniciar la app por primera vez, aparecerá la **guía interactiva** en forma de diálogos con transiciones animadas.
- Cada pantalla de la guía explica una sección específica de la app y cambia la vista en la actividad principal para mostrar la funcionalidad explicada.
- Se puede avanzar con el botón **"Siguiente"** o saltar la guía con **"Saltar Guía"**.
- La guía solo se muestra la primera vez que se usa la app (gracias a SharedPreferences).

## Easter Eggs

- Dentro de la sección **Personajes**, si mantienes pulsado sobre el personaje **Spyro**, salta una **animación escupiendo fuego**.
- Dentro de la sección **Coleccionables**, si tocas cuatro veces sobre las **gemas**, se desbloquea un **video secreto**.

## Conclusiones del Desarrollador
El desarrollo de esta aplicación ha sido un desafío considerable, especialmente en la implementación de la guía interactiva. Integrar transiciones fluidas entre los fragmentos de la guía, sincronizarlas con los cambios en la actividad principal y asegurarse de que la experiencia del usuario fuera intuitiva ha requerido un gran esfuerzo y múltiples iteraciones.

Además, la gestión de animaciones, efectos visuales con drawables y la detección precisa de eventos como los toques en los coleccionables han sido retos técnicos importantes. 

Otro aspecto complejo ha sido la persistencia de datos con SharedPreferences para asegurar de que la guía solo se muestre una vez. Resolver problemas relacionados con el almacenamiento de esta preferencia y la correcta gestión del ciclo de vida de los fragments ha llevado tiempo y esfuerzo.

## Capturas de pantalla

![Screenshot_20250303_203228](https://github.com/user-attachments/assets/1073a950-a301-4ec0-8af0-2c896778cdc0)
![Screenshot_20250303_203346](https://github.com/user-attachments/assets/b207adf2-06c4-4746-9984-3ea2ce4db019)
![Screenshot_20250303_203315](https://github.com/user-attachments/assets/167a7619-28a6-4c22-bcfb-b45babc519a5)

**¡Que disfrutes explorando el mundo de Spyro!**
