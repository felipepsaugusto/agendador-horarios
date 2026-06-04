markdown# Agendador de Horários API

API REST para gerenciamento de agendamentos de horários.

## Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok

## Como rodar localmente

### Pré-requisitos
- Java 21
- Maven

### Passos

1. Clone o repositório
   git clone https://github.com/felipepsaugusto/agendador-horarios.git

2. Configure o banco no `application.properties`
   spring.datasource.url=jdbc:h2:file:./data/agendamentos-db
   spring.datasource.username=sa
   spring.datasource.password=

3. Rode o projeto
   ./mvnw spring-boot:run

4. Acesse em `http://localhost:8080`

## Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /agendamentos?data=2024-01-15 | Busca agendamentos por dia |
| POST | /agendamentos | Cria um novo agendamento |
| PUT | /agendamentos | Altera um agendamento existente |
| DELETE | /agendamentos | Apaga um agendamento |

## Deploy

API disponível em: https://agendador-horarios-production.up.railway.app/