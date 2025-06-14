-- file: V2__insert_categories.sql

-- CREDIT categories
INSERT INTO category (name, type) VALUES ('SALARY', 'CREDIT');
INSERT INTO category (name, type) VALUES ('INTEREST', 'CREDIT');
INSERT INTO category (name, type) VALUES ('STG', 'CREDIT');
INSERT INTO category (name, type) VALUES ('LTG', 'CREDIT');
INSERT INTO category (name, type) VALUES ('GIFT', 'CREDIT');
INSERT INTO category (name, type) VALUES ('REFUND', 'CREDIT');

-- DEBIT categories
INSERT INTO category (name, type) VALUES ('LOAN', 'DEBIT');
INSERT INTO category (name, type) VALUES ('TAX', 'DEBIT');
INSERT INTO category (name, type) VALUES ('BILLS', 'DEBIT');
INSERT INTO category (name, type) VALUES ('MISC', 'DEBIT');
