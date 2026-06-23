# gestionbiblioteca

# Requerimientos
- Java 17
- Laragon
- MySQL configurado al puerto 3308

# Ejecucion
# 1. Crear bases de datos
> registrousuario
> 
> "CREATE DATABASE db_bibliotech_usuario_dev;"
> 
> "CREATE DATABASE db_bibliotech_usuario_test;"

> registroulibro
> 
> "CREATE DATABASE db_bibliotech_libro_dev;"
> 
> "CREATE DATABASE db_bibliotech_libro_test;"

> registropedido
> 
> "CREATE DATABASE db_bibliotech_pedidos_dev;"
> 
> "CREATE DATABASE db_bibliotech_pedidos_test;"

# 2. Cargar scripts de migracion 
(solo funcionales en bases de datos "dev")

Encontrados dentro de /src/main/resources/db/migration
