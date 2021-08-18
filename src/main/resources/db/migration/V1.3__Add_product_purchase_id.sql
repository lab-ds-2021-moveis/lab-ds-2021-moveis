-- Removes composite primary key and add a single column id

ALTER TABLE compra_produto DROP PRIMARY KEY;
ALTER TABLE compra_produto ADD COLUMN id_compra_produto BIGSERIAL NOT NULL PRIMARY KEY;
