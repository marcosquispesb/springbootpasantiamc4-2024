create table AUTH_ACTION
(
    ID            bigint        not null
        primary key,
    CREATED_BY    VARCHAR(255),
    CREATED_DATE  TIMESTAMP,
    DELETED       boolean         not null,
    MODIFIED_BY   VARCHAR(255),
    MODIFIED_DATE TIMESTAMP,
    VERSION       bigint,
    ACTION        VARCHAR(30) not null,
    BASE_ACTION   boolean         not null,
    CODE          VARCHAR(30) not null
        constraint UK_3DVE7OXEI4TYODTAMJ2DXHVOM
            unique
);

create table AUTH_RESOURCE
(
    ID                    bigint         not null
        primary key,
    CREATED_BY            VARCHAR(255),
    CREATED_DATE          TIMESTAMP,
    DELETED               boolean          not null,
    MODIFIED_BY           VARCHAR(255),
    MODIFIED_DATE         TIMESTAMP,
    VERSION               bigint,
    DESCRIPTION           VARCHAR(100),
    FRONTEND_CODE         VARCHAR(50),
    ICON                  VARCHAR(50)  not null,
    MENU_POSITION         integer         not null,
    NAME                  VARCHAR(100) not null,
    RESOURCE_STATUS       VARCHAR(255) not null,
    TYPE                  VARCHAR(255),
    URL                   VARCHAR(255) not null,
    ID_AUTH_RECURSO_PADRE bigint
        constraint FKN7L1AP1EI2KDMFFYTTI1BOI21
            references AUTH_RESOURCE
);

create table AUTH_ROLE
(
    ID            bigint         not null
        primary key,
    CREATED_BY    VARCHAR(255),
    CREATED_DATE  TIMESTAMP,
    DELETED       boolean          not null,
    MODIFIED_BY   VARCHAR(255),
    MODIFIED_DATE TIMESTAMP,
    VERSION       bigint,
    BASE_ROLE     boolean          not null,
    DESCRIPTION   VARCHAR(255) not null,
    NAME          VARCHAR(35)  not null,
    ROLE_STATUS   VARCHAR(255) not null
);

-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -

create table AUTH_USER
(
    ID                 bigint         not null
        primary key,
    CREATED_BY         VARCHAR(255),
    CREATED_DATE       TIMESTAMP,
    DELETED            boolean          not null,
    MODIFIED_BY        VARCHAR(255),
    MODIFIED_DATE      TIMESTAMP,
    VERSION            bigint,
    EMAIL              VARCHAR(250),
    GENERATED_PASSWORD boolean          not null,
    LASTNAME           VARCHAR(100) not null,
    NAME               VARCHAR(100) not null,
    PASSWORD           VARCHAR(500) not null,
    USER_STATUS        VARCHAR(255) not null,
    USERNAME           VARCHAR(20)  not null,
    ID_AUTH_ROLE       bigint         not null
        constraint FKM9GFVT69TKX7SV97PY7YBURFM
            references AUTH_ROLE
);

create table AUTH_RESOURCE_ACTION
(
    ID               bigint not null
        primary key,
    CREATED_BY       VARCHAR(255),
    CREATED_DATE     TIMESTAMP,
    DELETED          boolean  not null,
    MODIFIED_BY      VARCHAR(255),
    MODIFIED_DATE    TIMESTAMP,
    VERSION          bigint,
    ID_AUTH_ACTION   bigint not null
        constraint FKHSLQ86KWXVS6T0GDPLTBXXU8N
            references AUTH_ACTION,
    ID_AUTH_RESOURCE bigint not null
        constraint FK5LK75EHFBRVGIOP1K3K0J5C5T
            references AUTH_RESOURCE
);

create table AUTH_ROLE_RESOURCE
(
    ID               bigint not null
        primary key,
    CREATED_BY       VARCHAR(255),
    CREATED_DATE     TIMESTAMP,
    DELETED          boolean  not null,
    MODIFIED_BY      VARCHAR(255),
    MODIFIED_DATE    TIMESTAMP,
    VERSION          bigint,
    ID_AUTH_RESOURCE bigint not null
        constraint FK48MAURL11KVBA9VBUOD1U6T28
            references AUTH_RESOURCE,
    ID_AUTH_ROLE     bigint not null
        constraint FKOKCCT67V5DA5Q1T56MGFONBDV
            references AUTH_ROLE
);

-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -
-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -

create table AUTH_PRIVILEGE
(
    ID                    bigint not null
        primary key,
    CREATED_BY            VARCHAR(255),
    CREATED_DATE          TIMESTAMP,
    DELETED               boolean  not null,
    MODIFIED_BY           VARCHAR(255),
    MODIFIED_DATE         TIMESTAMP,
    VERSION               bigint,
    ID_AUTH_ACTION        bigint not null
        constraint FK6T83MOFU73AQ9WS2M5DLUKVQL
            references AUTH_ACTION,
    ID_AUTH_ROLE_RESOURCE bigint not null
        constraint FKB3VS2IM9XBOR7SY8ENG9PDUKN
            references AUTH_ROLE_RESOURCE
);