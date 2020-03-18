DROP TABLE IF EXISTS users;

CREATE TABLE USERS(
ID INT AUTO_INCREMENT  PRIMARY KEY,
FirstName VARCHAR(250) NOT NULL,
LastName VARCHAR(250) NOT NULL,
PhoneNumber VARCHAR(250),
EmailAddress VARCHAR(250),
Address VARCHAR(250),
SSN VARCHAR(250)
);

INSERT INTO USERS(FirstName,LastName,PhoneNumber,EmailAddress,Address,SSN)
values('Sai','Pothulapally','630-544-0942','sai.kkt@gmail.com','4308 Nutmeg Lane, Pittsburgh, PA','111-11-1111');

INSERT INTO USERS(FirstName,LastName,PhoneNumber,EmailAddress,Address,SSN)
values('John','Crissman','222-222-2222','john.crissman@gmail.com','1209 Arlington Ave, Chicago, IL','222-22-2222');

INSERT INTO USERS(FirstName,LastName,PhoneNumber,EmailAddress,Address,SSN)
values('Yalda','Koko','333-333-3333','yalda.koko@gmail.com','1290 Tudor Lane, St. Louis, MO','333-33-3333');