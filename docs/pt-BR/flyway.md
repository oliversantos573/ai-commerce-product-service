# Database Versioning with Flyway

# Visão Geral

O AI-Commerce utiliza o Flyway para controlar toda a evolução do banco de dados.

Toda alteração estrutural deve ser realizada através de migrations versionadas.

Nenhuma alteração deve ser feita manualmente em ambientes compartilhados.

---

# Objetivos

O Flyway garante:

- Versionamento do banco
- Histórico completo
- Reprodutibilidade
- Deploy automatizado
- Sincronização entre ambientes
- Segurança durante evoluções

---

# Estrutura

```
src

└── main

    └── resources

        └── db

            └── migration

                ├── V1__create_products_table.sql
                ├── V2__create_brands_table.sql
                ├── V3__create_categories_table.sql
                └── ...
```

---

# Convenção de Nomes

Toda migration deve seguir o padrão:

```
V<versão>__<descrição>.sql
```

Exemplos:

```
V1__create_products_table.sql

V2__create_brands_table.sql

V3__add_product_image.sql

V4__create_inventory_table.sql
```

---

# Primeira Migration

A primeira migration cria a tabela principal do serviço.

Tabela:

```
products
```

Campos:

- id
- sku
- name
- product_description
- brand_id
- category_id
- status
- created_at
- updated_at

Também são criados índices para melhorar a performance das consultas.

---

# Execução

Durante a inicialização da aplicação, o Spring Boot executa automaticamente o Flyway.

Fluxo:

```
Application Start

↓

Flyway

↓

Validação das migrations

↓

Execução das pendentes

↓

Aplicação inicia
```

---

# Histórico

O Flyway mantém um histórico das migrations executadas na tabela:

```
flyway_schema_history
```

Essa tabela registra:

- versão
- descrição
- script
- checksum
- data de execução
- sucesso da execução

---

# Checksum

Cada migration possui um checksum calculado automaticamente.

Se um arquivo de migration for alterado após já ter sido executado, o Flyway bloqueará a inicialização da aplicação.

Exemplo de erro:

```
Migration checksum mismatch
```

Essa validação protege a integridade do banco de dados.

---

# Boas Práticas

Uma migration executada nunca deve ser modificada.

Caso seja necessária uma alteração estrutural, uma nova migration deve ser criada.

Exemplo:

```
V5__add_price_column.sql
```

Em vez de alterar:

```
V1__create_products_table.sql
```

---

# Ambientes

O mesmo conjunto de migrations é utilizado em:

- Desenvolvimento
- Homologação
- Produção

Isso garante consistência entre todos os ambientes.

---

# Rollback

O Flyway Community Edition não realiza rollback automático.

Caso seja necessário desfazer uma alteração, deve-se criar uma nova migration corretiva.

Exemplo:

```
V6__remove_unused_column.sql
```

---

# Integração com Docker

Ao iniciar um novo ambiente com Docker Compose:

1. PostgreSQL é iniciado.
2. A aplicação conecta ao banco.
3. O Flyway valida o histórico.
4. As migrations pendentes são executadas.
5. A aplicação fica disponível.

---

# Benefícios

O uso do Flyway oferece:

- Evolução segura do banco
- Controle de versão
- Histórico auditável
- Deploy automatizado
- Facilidade para integração contínua (CI/CD)
- Redução de erros manuais

---

# Roadmap

Evoluções planejadas:

- Migrations para Brand Service
- Migrations para Category Service
- Migrations para Inventory Service
- Dados iniciais (seed data)
- Integração com Testcontainers
- Execução automática em pipelines CI/CD