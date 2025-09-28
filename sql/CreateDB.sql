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

-- Создание индекса (аналог NONCLUSTERED INDEX)
CREATE INDEX ON contracts (number);

-- Таблица users
CREATE TABLE users (
    id integer PRIMARY KEY,
    login varchar(50) NOT NULL UNIQUE,
    password varchar(255) NOT NULL
);
