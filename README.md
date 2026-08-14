# Bug Collection

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white) ![Spring%20Boot](https://img.shields.io/badge/Spring%20Boot-3-6DB33F?logo=springboot&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-JPA-4169E1?logo=postgresql&logoColor=white) ![Maven](https://img.shields.io/badge/Maven-build-C71A36?logo=apachemaven&logoColor=white)

REST-сервис каталога коллекций жуков на Java и Spring Boot: пользователи, экземпляры,
предложения обмена. Исходная версия сервиса, позже переписанная на Kotlin —
см. [beetle-management-service](https://github.com/m4themagics/beetle-management-service).

**Стек:** Java · Spring Boot · Spring Data JPA · Hibernate · PostgreSQL · Maven

## Архитектура

Трёхслойное разделение с интерфейсами между слоями:

```text
controller/    BeetleController — REST-эндпоинты
service/       BeetleService, UserService + реализации в Impl/
repository/    BeetleRepository, UserRepository — Spring Data JPA
model/         Beetle, User, ExchangeOffers — сущности
ResourceNotFoundException    отдельный тип для 404
```

`src/main/resources/query.sql` содержит исходные запросы, по которым проектировалась схема.

## Поток запроса

```mermaid
flowchart LR
    HTTP["HTTP-запрос"]
    C["controller<br/>REST-граница"]
    S["service<br/>бизнес-логика"]
    R["repository<br/>Spring Data JPA"]
    DB[("PostgreSQL")]
    E["ResourceNotFoundException<br/>-> 404"]

    HTTP --> C
    C --> S
    S --> R
    R --> DB
    S -.-> E
    E -.-> C
```

Каждый слой обращается только к соседнему снизу и знает его как интерфейс, а не как
реализацию — поэтому хранилище можно подменить, не трогая контроллеры.

## Запуск

Нужны JDK 17+ и PostgreSQL с базой `BugCollection`.

```bash
./mvnw spring-boot:run
```

Параметры подключения — в `src/main/resources/application.properties`; подставьте свои
учётные данные. Таблицы создаются автоматически (`ddl-auto=update`).

## Kotlin-версия

Тот же сервис на Kotlin, Gradle и с метриками Prometheus:
[beetle-management-service](https://github.com/m4themagics/beetle-management-service).
Репозитории удобно смотреть рядом — видно, что именно меняется при переносе
Spring-приложения с Java/Maven на Kotlin/Gradle.
