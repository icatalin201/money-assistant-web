insert into account_types (id, name, key) values (1, 'Cont bancar', 'bank');
insert into account_types (id, name, key) values (2, 'Card', 'card');
insert into account_types (id, name, key) values (3, 'Cash', 'cash');
insert into account_types (id, name, key) values (4, 'Card de debit', 'debit_card');
insert into account_types (id, name, key) values (5, 'Asigurare', 'insurance');
insert into account_types (id, name, key) values (6, 'Investitii', 'investments');
insert into account_types (id, name, key) values (7, 'Economii', 'savings');
insert into account_types (id, name, key) values (8, 'Altele', 'others');
insert into accounts (id, name, amount, type_id) values (1, 'Cash', 0, 3);
insert into categories (id, name, type) values (3, 'Salarii', 'income');
insert into categories (id, name, type) values (2, 'Diverse', 'expense');

