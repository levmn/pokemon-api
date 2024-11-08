DROP TABLE tb_pokemon;
DROP SEQUENCE tb_pokemon_id_seq;

CREATE TABLE tb_pokemon (
    id        INTEGER NOT NULL,
    name      VARCHAR2(100) NOT NULL,
    types     VARCHAR2(100) NOT NULL,
    abilities VARCHAR2(100) NOT NULL
);

ALTER TABLE tb_pokemon ADD CONSTRAINT tb_pokemon_id_pk PRIMARY KEY ( id );

CREATE SEQUENCE tb_pokemon_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tb_pokemon_id_trg BEFORE
    INSERT ON tb_pokemon
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := tb_pokemon_id_seq.nextval;
END;