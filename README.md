# Erida

## Описание проекта
Проект представляет собой корпоративную платформу для управления договорной деятельностью и внутренним документооборотом. Он предназначен для автоматизации процессов работы с договорами, централизованного хранения документов и снижения рисков.
Система предоставляет возможность регистрировать, хранить и отслеживать договоры, а также автоматически генерировать на их основе сопутствующие документы, такие как акты выполненных работ. Для администраторов система позволяет настраивать права доступа пользователей через создание и назначение именованных наборов прав.
Неавторизованные пользователи (Гости) могут авторизоваться в системе, чтобы получить доступ к ее функционалу.
Пользователи могут взаимодействовать друг с другом через встроенный мессенджер, обмениваться документами и выполнять действия в рамках выданных им прав. 
Администраторы занимаются добавлением новых шаблонов договоров, регистрацией договоров в базе данных (БД) и генерацией актов. Они могут читать и редактировать любые данные в БД для поддержания их актуальности, а также выдавать пользователям доступ к части или всем правам администратора и конфигурировать и сохранять наборы разрешений (роли) для стандартизации управления доступом.
Это помогает организации эффективно управлять документами, контролировать доступ к информации и автоматизировать рутинные операции.

## Стек используемых технологий

### Backend
- **Фреймворк**: [Spring Boot]
- **Язык**: [Java]

### Frontend
- **Фреймворк**: [React/Vue/Angular/другой]
- **Язык**: [TypeScript/JavaScript]


Роли пользователей и варианты использования
Роли в системе
Гость - неавторизованный пользователь

Пользователь - авторизованный сотрудник

Администратор - пользователь с расширенными правами

Диаграмма вариантов использования
https://%D1%81%D1%81%D1%8B%D0%BB%D0%BA%D0%B0_%D0%BD%D0%B0_%D0%B4%D0%B8%D0%B0%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D1%83_%D0%B2%D0%B0%D1%80%D0%B8%D0%B0%D0%BD%D1%82%D0%BE%D0%B2_%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F.png

Описание действий по ролям
Гость
✅ Регистрация в системе

✅ Авторизация в системе

Пользователь
✅ Просмотр справочника организаций

✅ Просмотр справочника банков

✅ Просмотр справочника счетов

✅ Просмотр справочника услуг

✅ Просмотр справочника договоров

✅ Фильтрация договоров по дате

✅ Фильтрация договоров по типу

✅ Экспорт данных

Администратор
✅ Все действия пользователя

✅ Добавление/редактирование организаций

✅ Добавление/редактирование банков

✅ Добавление/редактирование счетов

✅ Добавление/редактирование услуг

✅ Добавление/редактирование договоров

✅ Управление пользователями

Схема базы данных
ER-диаграмма


Описание таблиц

organizations
id - SERIAL PRIMARY KEY

unp - VARCHAR(9) UNIQUE NOT NULL

full_name - VARCHAR(255) NOT NULL

short_name - VARCHAR(255) NOT NULL

address - VARCHAR(255)

legal_address - VARCHAR(255)

postal_address - VARCHAR(255)

phone - VARCHAR(255)

created_at - TIMESTAMP

updated_at - TIMESTAMP

banks
id - SERIAL PRIMARY KEY

bic - VARCHAR(8) NOT NULL

bank_name - VARCHAR(255) NOT NULL

created_at - TIMESTAMP

bank_accounts
id - SERIAL PRIMARY KEY

unp - VARCHAR(9) NOT NULL

account_number - VARCHAR(28) NOT NULL

bank_id - INTEGER REFERENCES banks(id)

is_budget - BOOLEAN NOT NULL

created_at - TIMESTAMP

services
id - SERIAL PRIMARY KEY

name - VARCHAR(255) NOT NULL

cost_without_vat - DECIMAL(10,2) NOT NULL

unit - VARCHAR(50) NOT NULL

created_at - TIMESTAMP

contracts
id - SERIAL PRIMARY KEY

contract_number - VARCHAR(20) NOT NULL

contract_type - VARCHAR(100) NOT NULL

contract_date - DATE NOT NULL

valid_from - DATE NOT NULL

valid_to - DATE NOT NULL

bank_account_id - INTEGER REFERENCES bank_accounts(id)

service_id - INTEGER REFERENCES services(id)

workplaces_count - INTEGER NOT NULL

electronic_acts - BOOLEAN DEFAULT false

termination_type - VARCHAR(100)

created_at - TIMESTAMP

users
id - SERIAL PRIMARY KEY

username - VARCHAR(100) UNIQUE NOT NULL

email - VARCHAR(255) UNIQUE NOT NULL

password_hash - VARCHAR(255) NOT NULL

role - VARCHAR(50) DEFAULT 'user'

created_at - TIMESTAMP


## API сервера

### Основные endpoints:
- `POST /api/auth/login` - Авторизация
- `POST /api/auth/register` - Регистрация
- `GET /api/organizations` - Справочник организаций
- `GET /api/banks` - Справочник банков
- `GET /api/bank-accounts` - Справочник счетов
- `GET /api/contracts` - Справочник договоров
- `GET /api/contracts/filter` - Фильтрация договоров

# API Documentation

## Аутентификация

### Регистрация
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "string",
  "password": "string",
  "email": "string"
}
```

### Авторизация
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "string",
  "password": "string"
}
```

Response:
```json
{
  "token": "string",
  "expires_in": 3600
}
```

## НСИ - Нормативно-справочная информация

### Справочник организаций
```http
GET /api/organizations
Authorization: Bearer {token}
```

Response:
```json
[
  {
    "unp": "123456789",
    "full_name": "Полное наименование организации",
    "short_name": "Сокращенное наименование",
    "phone": "+375 29 123-45-67",
    "address": "Адрес организации",
    "legal_address": "Юридический адрес",
    "postal_address": "Почтовый адрес"
  }
]
```

### Справочник банков
```http
GET /api/banks
Authorization: Bearer {token}
```

Response:
```json
[
  {
    "id": 1,
    "bic": "BELBBY2X",
    "bank_name": "Наименование банка"
  }
]
```

### Справочник счетов
```http
GET /api/bank-accounts
Authorization: Bearer {token}
```

Response:
```json
[
  {
    "id": 1,
    "unp": "123456789",
    "account_number": "BY12ALFA20345678901234567890",
    "bic": "BELBBY2X",
    "bank_name": "Наименование банка",
    "is_budget": true
  }
]
```

### Справочник видов услуг
```http
GET /api/services
Authorization: Bearer {token}
```

Response:
```json
[
  {
    "id": 1,
    "code": "SRV001",
    "name": "Наименование услуги",
    "unit": "шт.",
    "cost_without_vat": 100.00,
    "vat": 20.00,
    "cost_with_vat": 120.00
  }
]
```

### Справочник договоров
```http
GET /api/contracts
Authorization: Bearer {token}
```

Response:
```json
[
  {
    "contract_number": "ДГ-2024-001",
    "contract_date": "2024-01-15",
    "valid_from": "2024-02-01",
    "valid_to": "2024-12-31",
    "unp": "123456789",
    "organization_full_name": "Полное наименование организации",
    "service_type": "Вид услуг",
    "workplaces_count": 10,
    "funding_source": "бюджет",
    "electronic_acts": true,
    "act_prefix": "АКТ"
  }
]
```

## Фильтрация договоров

### Фильтрация по дате

#### Действующие договоры
```http
GET /api/contracts/active
Authorization: Bearer {token}
```

#### Договоры за период
```http
GET /api/contracts/filter-by-date
Authorization: Bearer {token}
Content-Type: application/json

{
  "start_date": "2024-01-01",
  "end_date": "2024-12-31"
}
```

### Фильтрация по типу договора
```http
GET /api/contracts/filter-by-type
Authorization: Bearer {token}
Content-Type: application/json

{
  "contract_type": "тип_договора"
}
```

### Фильтрация по типу расторжения
```http
GET /api/contracts/filter-by-termination
Authorization: Bearer {token}
Content-Type: application/json

{
  "termination_type": "тип_расторжения"
}
```

### Комплексная фильтрация
```http
GET /api/contracts/filter
Authorization: Bearer {token}
Content-Type: application/json

{
  "start_date": "2024-01-01",
  "end_date": "2024-12-31",
  "contract_type": "тип_договора",
  "termination_type": "тип_расторжения",
  "funding_source": "бюджет",
  "electronic_acts": true
}
```

## Модели данных

### Организация
- `unp` (string, 9 цифр) - УНП, уникальный
- `full_name` (string, 255) - Полное наименование, обязательно
- `short_name` (string, 255) - Сокращенное наименование, обязательно
- `address` (string, 255, optional) - Адрес
- `legal_address` (string, 255, optional) - Юридический адрес
- `postal_address` (string, 255, optional) - Почтовый адрес
- `phone` (string, 255, optional) - Телефон

### Банк
- `id` (integer) - Уникальный порядковый номер
- `bic` (string, ≤8) - БИК, латинские заглавные буквы и цифры
- `bank_name` (string, 255) - Наименование банка

### Счет
- `id` (integer) - Уникальный порядковый номер
- `unp` (string, 9 цифр) - УНП организации
- `account_number` (string, 28) - Номер счета (2 буквы + 2 цифры + 4 буквы + 20 цифр)
- `bank_id` (integer) - Ссылка на банк
- `is_budget` (boolean) - Признак "Бюджет/внебюджет" (1/0)

### Договор
- `id` (integer) - Уникальный номер
- `contract_number` (string, 20) - Номер договора
- `contract_type` (string) - Тип договора
- `contract_date` (date) - Дата договора
- `valid_from` (date) - Действует с
- `valid_to` (date) - Действует по
- `bank_account_id` (integer) - Ссылка на счет
- `service_id` (integer) - Ссылка на услугу
- `workplaces_count` (integer) - Количество рабочих мест
- `electronic_acts` (boolean) - Признак "ЭлектроАкты"
- `termination_type` (string) - Тип окончания договора

### Услуга
- `id` (integer) - Уникальный порядковый номер
- `name` (string) - Название
- `cost_without_vat` (decimal) - Стоимость без НДС
- `unit` (string) - Единица измерения

## Коды ответов

- `200` - Успешный запрос
- `201` - Успешное создание
- `400` - Неверный запрос
- `401` - Не авторизован
- `404` - Ресурс не найден
- `500` - Внутренняя ошибка сервера

## Заголовки

Все запросы (кроме аутентификации) требуют заголовок авторизации:
```
Authorization: Bearer {token}
```

Для запросов с телом требуется указать:
```
Content-Type: application/json
```
