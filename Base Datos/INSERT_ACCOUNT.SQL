/*INSERT OWNER*/
INSERT INTO OWNER (owner) VALUES (7612333392);
INSERT INTO OWNER (owner) VALUES (7612333458);
INSERT INTO OWNER (owner) VALUES (761233785);

/*INSERT ACCOUNT*/

INSERT INTO ACCOUNT (account, balance, amount, createdAt, idOwner) VALUES (123456789, 45450, 10000, '2021-05-16', 1);
INSERT INTO ACCOUNT (account, balance, amount, createdAt, idOwner) VALUES (987654321, 52465, 10000, '2021-05-16', 2);
INSERT INTO ACCOUNT (account, balance, amount, createdAt, idOwner) VALUES (13456658, 32442, 10000, '2021-05-16', 3);

/*INSERT TRANSACTION*/

INSERT INTO TRANSACTION (idFromAccount, idAccount, amount, sentAt) VALUES (1, 2, 876.88, '2021-05-16');
INSERT INTO TRANSACTION (idFromAccount, idAccount, amount, sentAt) VALUES (1, 2, 234.66, '2021-05-16');
INSERT INTO TRANSACTION (idFromAccount, idAccount, amount, sentAt) VALUES (1, 2, 234.88, '2021-05-16');