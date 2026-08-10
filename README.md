# Bug Collection

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
