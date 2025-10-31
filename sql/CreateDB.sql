-- Таблица services
CREATE TABLE services (
    id integer PRIMARY KEY,
    name varchar(20),
    cost float CHECK (cost >= 0.0),
    unit varchar(20)
);

-- Таблица banks
CREATE TABLE banks (
    id integer PRIMARY KEY,
    BIC varchar(8) CHECK (BIC ~ '^[A-Z0-9]+$'),
    name varchar(255)
);

-- Таблица organisations
CREATE TABLE organisations (
    UNP integer PRIMARY KEY CHECK (UNP >= 100000000 AND UNP <= 999999999),
    name varchar(255) NOT NULL,
    short_name varchar(255),
    address varchar(255),
    legal_address varchar(255),
    mail_address varchar(255),
    phone_number varchar(255)
);

-- Таблица bank_accounts
CREATE TABLE bank_accounts (
    id integer PRIMARY KEY,
    code varchar(20),
    is_budget integer CHECK (is_budget = 0 OR is_budget = 1),
    bank_id integer REFERENCES banks(id),
    organisation_UNP integer CHECK (organisation_UNP >= 100000000 AND organisation_UNP <= 999999999) REFERENCES organisations(UNP)
);

-- Таблица contracts
CREATE TABLE contracts (
    id integer PRIMARY KEY,
    number varchar(20) NOT NULL UNIQUE,
    type varchar(20) NOT NULL CHECK (type IN ('ГРС', 'Клиент ТК')),
    approval_date timestamp NOT NULL,
    begin_date timestamp NOT NULL,
    end_date timestamp NOT NULL,
    workplaces_count integer CHECK (workplaces_count >= 0),
    is_electro_acts integer CHECK (is_electro_acts = 0 OR is_electro_acts = 1),
    expiry_type varchar(20) NOT NULL DEFAULT 'Окончание срока действия' 
        CHECK (expiry_type IN ('Окончание срока действия', 'Отказ от заключения', 'Ликвидация организации')),
    service_id integer NOT NULL REFERENCES services(id),
    bank_account_id integer NOT NULL REFERENCES bank_accounts(id),
    CHECK (begin_date <= end_date)
);

-- Таблица users
CREATE TABLE users (
    id integer PRIMARY KEY,
    login varchar(50) NOT NULL UNIQUE,
    password varchar(255) NOT NULL
);

CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

-- ИНДЕКСЫ КОНТРАКТОВ
-- Для поиска по датам (часто используется в отчетах и фильтрации)
CREATE INDEX idx_contracts_dates ON contracts(begin_date, end_date);

-- Для фильтрации по типу договора
CREATE INDEX idx_contracts_type ON contracts(type);

-- Для поиска по service_id (внешний ключ)
CREATE INDEX idx_contracts_service_id ON contracts(service_id);

-- Для поиска по bank_account_id (внешний ключ)
CREATE INDEX idx_contracts_bank_account_id ON contracts(bank_account_id);

-- Для проверки активности договоров
CREATE INDEX idx_contracts_active ON contracts(begin_date, end_date) WHERE end_date >= CURRENT_DATE;


-- ИНДЕКСЫ БАНКОВ
-- Для поиска по организации
CREATE INDEX idx_bank_accounts_organisation ON bank_accounts(organisation_UNP);

-- Для поиска по банку
CREATE INDEX idx_bank_accounts_bank ON bank_accounts(bank_id);

-- Для фильтрации бюджетных счетов
CREATE INDEX idx_bank_accounts_budget ON bank_accounts(is_budget);

-- ИНДЕКСЫ ДЛЯ СЧЕТОВ В БАНКАХ
-- Для поиска по имени организации
CREATE INDEX idx_organisations_name ON organisations(name);

-- Для поиска по короткому имени
CREATE INDEX idx_organisations_short_name ON organisations(short_name);

-- ИНДЕКСЫ ДЛЯ БАНКОВ
-- Для поиска по БИК
CREATE INDEX idx_banks_bic ON banks(BIC);

-- Для поиска по названию банка
CREATE INDEX idx_banks_name ON banks(name);

-- ИНДЕКСЫ ДЛЯ УСЛУГ
-- Для поиска по названию услуги
CREATE INDEX idx_services_name ON services(name);

-- Для поиска по стоимости
CREATE INDEX idx_services_cost ON services(cost);

-- ИНДЕКСЫ ДЛЯ РОЛЕЙ ПОЛЬЗОВАТЕЛЕЙ
-- Для поиска ролей пользователя
CREATE INDEX idx_user_roles_user_id ON user_roles(user_id);

-- Для поиска пользователей с определенной ролью
CREATE INDEX idx_user_roles_role_id ON user_roles(role_id);
