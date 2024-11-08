--- Dropar tabelas e sequences
DROP TABLE tb_pokemon;
DROP TABLE tb_trainer;
DROP TABLE tb_trainer_team
DROP SEQUENCE tb_pokemon_id_seq;
DROP SEQUENCE tb_trainer_id_seq;

--- Criar tabela para treinador
CREATE TABLE tb_trainer (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

ALTER TABLE tb_trainer ADD CONSTRAINT tb_trainer_id_pk PRIMARY KEY (id);

CREATE SEQUENCE tb_trainer_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tb_trainer_id_trg BEFORE
    INSERT ON tb_trainer
    FOR EACH ROW
    WHEN (new.id IS NULL)
BEGIN
    :new.id := tb_trainer_id_seq.nextval;
END;

-- Criar tabela para Pokémon
CREATE TABLE tb_pokemon (
    id        INTEGER NOT NULL,
    name      VARCHAR2(100) NOT NULL,
    types     VARCHAR2(100) NOT NULL,
    abilities VARCHAR2(100) NOT NULL
);

ALTER TABLE tb_pokemon ADD CONSTRAINT tb_pokemon_id_pk PRIMARY KEY (id);

CREATE SEQUENCE tb_pokemon_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tb_pokemon_id_trg BEFORE
    INSERT ON tb_pokemon
    FOR EACH ROW
    WHEN (new.id IS NULL)
BEGIN
    :new.id := tb_pokemon_id_seq.nextval;
END;

-- Criar tabela para time

CREATE TABLE tb_trainer_team (
    trainer_id INT,
    pokemon_id INT,
    PRIMARY KEY (trainer_id, pokemon_id),
    FOREIGN KEY (trainer_id) REFERENCES tb_trainer(id),
    FOREIGN KEY (pokemon_id) REFERENCES tb_pokemon(id)
);
