
INSERT INTO USER_DETAILS(BIRTH_DATE, ID, NAME)
VALUES
  (CURRENT_DATE(), 1001, 'JEWEL'),
  (CURRENT_DATE(), 1002, 'RANA'),
  (CURRENT_DATE(), 1003, 'SHOHEL'),
  (CURRENT_DATE(), 1004, 'SELIM');


INSERT INTO POST(ID, USER_ID, DESCRIPTION)
VALUES
  (2001, 1001, 'I WANT TO LEARN AWS'),
  (2005, 1001, 'I WANT TO LEARN ML'),
  (2002, 1002, 'I WANT TO LEARN SPRING BOOT'),
  (2003, 1003, 'I WANT TO LEARN MICROSERVICES'),
  (2004, 1004, 'I WANT TO LEARN SPRING CLOUD');