# FastFood
Trabajo práctico grupal para la materia Programación II en la Universidad Argentina de la Empresa. Desarrollo de un programa en Java en el IDE Eclipse para gestionar los turnos de atención en un Fast Food. 

# Enunciado
Desarrollar un programa para gestionar los turnos para atención de una Fast Food. El cliente retira un ticket de una máquina expendedora y se queda esperando a que lo llamen para ser atendido por alguno de los 10 puntos de atención. Los tickets están compuestos por un prefijo según el menú y un número el cual será la hora y los minutos en donde se realizó el pedido (formato HHMM). Prefijos para las nomenclaturas de los tickets:

1- **Venta de Plato del día**. Prefijo *PDL* Prioridad *200*.

2- **Pastas**. Prefijo *PAS*. Prioridad: *140*.

3- **Hamburguesas**. Prefijo *AM*. Prioridad *40*. 

4- **Resto de platos**. Prefijo *RDP*. Prioridad *-hhmm*.

# Funcionamiento del sistema
En primer lugar, el sistema genera de manera aleatoria 100 tickets con los distintos platos ofrecidos por el FastFood, detallando lo siguiente:

-`ID TICKET`: número que identifica inequívocamente al ticket.

-`Ticket`: pedido realizado con la nomenclatura detallada en el enunciado.

-`Prioridad`: orden de importancia a partir del plato pedido.

En estos 100 tickets, el sistema calcula 2 clientes por minuto.
Luego, a partir de las prioridades va llamando cada dos segundos a cada cliente a un puesto determinado, indicando la hora del llamado y el ID Ticket. El sistema finaliza cuando los 100 clientes han sido llamados a un puesto.

## Aclaración
Para que la hora del pedido (detallada en el ticket) y la hora del llamado sean coherentes, el sistema deberia llamar a cada cliente, como mínimo, cada 30 segundos. Hacer esto nos parecía en vano, ya que el sistema tardaría 50 minutos en llamar a todos y este es un proyecto ficticio, en donde el propósito es que el sistema funcione correctamente más allá de la coherencia en el tiempo entre llamadas.

## Como usar el sistema

En primer lugar hay que clonar el repositorio a un repositorio local.
Luego, abrir tu IDE de preferencia (nosotros usamos Eclipse) y ejecutar el archivo [testTPO](src/tpo/testTPO.java).

# Sugerencias

Si tenes alguna idea para mejorar nuestro proyecto o encontraste algún error, no dudes hacer un pull request. Tu retroalimentación nos ayuda a crear una experiencia aún mejor y a mejorar constantemente. También puedes contactarme en mi [email](mailto:camolins2404@gmail.com).

