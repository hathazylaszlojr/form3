CREATE TABLE ACCOUNT (
  ACCOUNT_NUMBER                  VARCHAR(40) PRIMARY KEY,
  ACCOUNT_NAME                    VARCHAR(100),
  ACCOUNT_NUMBER_CODE             VARCHAR(4),
  ACCOUNT_TYPE                    INT,
  ADDRESS                         VARCHAR(100),
  BANK_ID                         INT, 
  BANK_ID_CODE                    VARCHAR(5),
  NAME                            VARCHAR(40)
);

CREATE TABLE CHARGES (
  ID                              INT PRIMARY KEY,
  PAYMENT_ID                      BINARY(16),
  TYPE                            VARCHAR(8),
  AMOUNT                          FLOAT,
  CURRENCY                        VARCHAR(3)
);

CREATE TABLE PAYMENT (
  ID                              BINARY(16) PRIMARY KEY,
  ORGANISATION_ID                 BINARY(16),
  VERSION                         INT,
  CI_BEARER_CODE                  VARCHAR(10),
  TYPE                            VARCHAR(10),
  AMOUNT                          FLOAT,
  RECEIVER_CHARGE_ID_FK           INT FOREIGN KEY REFERENCES CHARGES(ID),
  BENEFICIARY_ACCOUNT_NUMBER_FK   VARCHAR(40) FOREIGN KEY REFERENCES ACCOUNT(ACCOUNT_NUMBER),
  CURRENCY                        VARCHAR(3),
  DEBTOR_PARTY_ACCOUNT_NUMBER_FK  VARCHAR(40) FOREIGN KEY REFERENCES ACCOUNT(ACCOUNT_NUMBER),
  END_TO_END_REFERENCE            VARCHAR(100),
  FX_CONTRACT_REFERENCE           VARCHAR(5),
  FX_EXCHANGE_RATE                FLOAT,
  FX_ORIGINAL_AMOUNT              FLOAT,
  FX_ORIGINAL_CURRENCY            VARCHAR(3),
  NUMERIC_REFERENCE               VARCHAR(10),
  PAYMENT_ID                      VARCHAR(20),
  PAYMENT_PURPOSE                 VARCHAR(100),
  PAYMENT_SCHEME                  VARCHAR(3),
  PAYMENT_TYPE                    VARCHAR(10),
  PROCESSING_DATE                 TIMESTAMP,
  REFERENCE                       VARCHAR(100),
  SCHEME_PAYMENT_SUB_TYPE         VARCHAR(100),
  SCHEME_PAYMENT_TYPE             VARCHAR(100),
  SPONSOR_ACCOUNT_NUMBER_FK       VARCHAR(40) FOREIGN KEY REFERENCES ACCOUNT(ACCOUNT_NUMBER)
);
