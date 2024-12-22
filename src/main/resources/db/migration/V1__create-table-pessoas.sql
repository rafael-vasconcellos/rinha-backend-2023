CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE OR REPLACE FUNCTION generate_searchable(_nome VARCHAR, _apelido VARCHAR, _stack JSON)
    RETURNS TEXT AS $$ -- os dois cifrões são um delimitador usado para envolver o corpo do código da função, pode ser substituído por qualquer outro delimitador válido
    BEGIN
        RETURN LOWER(_nome || ' ' || _apelido || ' ' || COALESCE(_stack::text, '')); -- operador de concatenação em PostgreSQL, JSON é automaticamente convertido em string
    END;
$$ LANGUAGE plpgsql IMMUTABLE;
-- PL/pgSQL: uma linguagem procedural do PostgreSQL
-- IMMUTABLE: para os mesmos valores de entrada, a função sempre retorna o mesmo resultado.

CREATE TABLE IF NOT EXISTS pessoas (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    apelido TEXT UNIQUE NOT NULL,
    nome TEXT NOT NULL,
    nascimento DATE NOT NULL,
    stack JSON,
    searchable text GENERATED ALWAYS AS (generate_searchable(nome, apelido, stack)) STORED -- ao invés de cálculo sob demanda
);

-- Esquema: agrupa objetos dentro de um banco de dados.
CREATE INDEX IF NOT EXISTS idx_pessoas_searchable ON public.pessoas USING gist (searchable public.gist_trgm_ops (siglen='64'));

CREATE UNIQUE INDEX IF NOT EXISTS pessoas_apelido_index ON public.pessoas USING btree (apelido);