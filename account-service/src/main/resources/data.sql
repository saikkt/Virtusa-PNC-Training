DROP TABLE IF EXISTS Accounts;

CREATE TABLE Accounts(
ID INT AUTO_INCREMENT  PRIMARY KEY,
AccountNumber INT UNIQUE NOT NULL,
UserID INT NOT NULL,
AccountType VARCHAR(250) NOT NULL,
Balance DECIMAL NOT NULL
);

INSERT INTO Accounts(AccountNumber,UserID,AccountType,Balance)
VALUES
(1111,1,'Checking',2000.67);
INSERT INTO Accounts(AccountNumber,UserID,AccountType,Balance)
VALUES
(1112,1,'Savings',1000.07);

INSERT INTO Accounts(AccountNumber,UserID,AccountType,Balance)
VALUES
(2222,2,'Checking',1080.77);

INSERT INTO Accounts(AccountNumber,UserID,AccountType,Balance)
VALUES
(3333,3,'Checking',5000.60);
