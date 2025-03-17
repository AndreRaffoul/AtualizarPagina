
# UPDATE usuario
UPDATE usuario SET senha = '$2a$10$wHGFhDwspP8Ej1F9x9iJZOJkI.3ptNC2ktHFJ14N8v3CEj.aLe8pa' WHERE login = 'AlexTeste';

# Deletar usuario
DELETE FROM usuario WHERE login = 'Raffoul'; 

# Deletar usuario_role  (role_user)
DELETE FROM unique_role_user WHERE usuario_id = (SELECT id FROM usuario WHERE login = 'Raffoul');


# Deletar usuario_role  (role_user)
DELETE FROM unique_role_user WHERE usuario_id = 12;

# Deletar usuario_role  (role_user)
SELECT id FROM usuario WHERE login = 'Raffoul'; 

# INSERT INTO usuario_role
INSERT INTO role (id, nome_role) VALUES (3, 'ROLE_MANAGER');