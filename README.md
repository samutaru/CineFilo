CINE-FILO

Enunciado del Proyecto: Cine-Filo 
Se pretende crear una aplicación llamada Cine-Filo desarrollada en Java que permite a los usuarios la reserva de entradas para proyecciones de películas. La plataforma implementa un sistema de registro e inicio de sesión, notificaciones automáticas, y la gestión de descuentos y promociones. Además de ofrecer opciones estándar y premium en la reserva de entradas también se implementa un sistema de recomendaciones personalizado para cada usuario. 

Funcionalidades Claves:
1. Registro y Login de usuarios: Los usuarios pueden registrarse con su email o redes sociales y autenticarse en la aplicación.
2. Gestión de reservas: Los clientes pueden seleccionar una película, horario y tipo de sala (estándar o premium).
3. Notificaciones: Una vez completada la reserva, los usuarios reciben un ticket digital por correo o WhatsApp.
4. Descuentos y Promociones: Se aplican descuentos dependiendo del tipo de usuario (ej. clientes frecuentes).
5. Métodos de pago: Se integran diferentes métodos de pago como tarjeta de crédito, PayPal o efectivo en local.
6. Historial de compras y recomendaciones: Se almacenan las compras anteriores y se recomienda contenido basado en el historial del usuario.








Patrones empleados en cada Funcionalidad
Singleton - Gestión de Sesión
Se utiliza Singleton para garantizar que solo exista una instancia del usuario autenticado en la aplicación.
Factory Method - Creación de Tickets
El sistema de tickets emplea el patrón Factoría para generar distintos tipos de entradas: estándar, premium y con descuento.
Observer - Notificaciones de Reserva
Cuando un usuario completa una reserva, el sistema envía automáticamente notificaciones por WhatsApp o correo electrónico, utilizando el patrón Observador.
Strategy - Métodos de Pago
Los clientes pueden pagar con tarjeta de crédito, PayPal o efectivo, implementando el patrón Estrategia para definir los diferentes métodos de pago.
Decorator - Aplicación de Descuentos
El sistema de descuentos emplea el patrón Decorator para modificar dinámicamente el precio del ticket si el usuario tiene beneficios especiales.
Command - Historial de Compras y Recomendaciones
Cada reserva realizada se almacena como un comando ejecutable en un historial. Luego, el sistema puede deshacer la última compra o usar ese historial para recomendar películas al usuario de manera personalizada.







