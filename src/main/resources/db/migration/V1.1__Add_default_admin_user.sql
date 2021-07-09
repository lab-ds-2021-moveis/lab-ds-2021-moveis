-- Creates the admin
-- Login (Credential): admin
-- Password: "admin123"
-- Password is using default BCrypt algorithm
INSERT INTO funcionario(id_funcionario, nome, senha, cargo, credencial) VALUES (funcionario_id_seq.nextval, 'Admin', '$2a$10$fDPpExO.vn.nNRlOuL0fpOSlHxTYDDpj4RP2SFjyORYhUUWV6xPOW', 'MANAGEMENT', 'admin');