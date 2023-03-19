INSERT INTO proveedores (id, nombre, fechaalta) VALUES (1, 'Coca-cola', '20230101');
INSERT INTO proveedores (id, nombre, fechaalta) VALUES (2, 'Pepsi', '20230102');
INSERT INTO proveedores (id, nombre, fechaalta) VALUES (3, 'Redbull', '20230103');

INSERT INTO clientes (id, nombre) VALUES (1, 'Cliente 1');
INSERT INTO clientes (id, nombre) VALUES (2, 'Cliente 2');
INSERT INTO clientes (id, nombre) VALUES (3, 'Cliente 3');
INSERT INTO clientes (id, nombre) VALUES (4, 'Cliente 4');
INSERT INTO clientes (id, nombre) VALUES (5, 'Cliente 5');
INSERT INTO clientes (id, nombre) VALUES (6, 'Cliente 6');

INSERT INTO clientesproveedores (cliente_id, proveedor_id) VALUES (5, 1);
INSERT INTO clientesproveedores (cliente_id, proveedor_id) VALUES (5, 2);
INSERT INTO clientesproveedores (cliente_id, proveedor_id) VALUES (6, 3);