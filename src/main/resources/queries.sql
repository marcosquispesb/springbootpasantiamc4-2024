SELECT * FROM auth_role;
SELECT * FROM auth_action;
SELECT * FROM auth_user;
SELECT * FROM auth_resource order by id asc;
SELECT * FROM auth_resource WHERE id_auth_recurso_padre IS null;
SELECT a.* FROM auth_resource a INNER JOIN auth_resource b ON a.id_auth_recurso_padre = b.id
WHERE b.name='Seguridad' order by id asc;
SELECT * FROM auth_role_user;

ALTER TABLE auth_user DROP COLUMN id_auth_role;
