DROP TABLE service;
DROP TABLE address;
DROP TABLE organization;
DROP TABLE category;

CREATE TABLE address (
        id CHARACTER VARYING(36) NOT NULL,
        organizationid CHARACTER VARYING(36) NOT NULL,
        street CHARACTER VARYING(100),
        city CHARACTER VARYING(100),
        state CHARACTER VARYING(2), zip CHARACTER VARYING(10),
        PRIMARY KEY (id));
CREATE TABLE category (
        id CHARACTER VARYING(36) NOT NULL,
        name CHARACTER VARYING(100),
        count INTEGER,
        PRIMARY KEY (id));
CREATE TABLE organization (
        id CHARACTER VARYING(36) NOT NULL,
        name CHARACTER VARYING(100),
        url CHARACTER VARYING(100),
        PRIMARY KEY (id));
CREATE TABLE service (
        id CHARACTER VARYING(36) NOT NULL,
        addressid CHARACTER VARYING(36) NOT NULL,
        categoryid CHARACTER VARYING(36) NOT NULL,
        hours CHARACTER VARYING(100),
        notes CHARACTER VARYING(500),
        PRIMARY KEY (id));
ALTER TABLE "address" ADD CONSTRAINT address_fk1 FOREIGN KEY ("organizationid") REFERENCES "organization" ("id") ON DELETE CASCADE;
ALTER TABLE "service" ADD CONSTRAINT service_fk1 FOREIGN KEY ("addressid") REFERENCES "address" ("id") ON DELETE CASCADE;
ALTER TABLE "service" ADD CONSTRAINT service_fk2 FOREIGN KEY ("categoryid") REFERENCES "category" ("id") ON DELETE RESTRICT;

INSERT INTO category (id, name, count) VALUES ('a246bfe9-9cb8-4c57-8909-5c25e621b6b6', 'Tents', 0);
INSERT INTO category (id, name, count) VALUES ('74e78dce-751c-4787-8239-4760b134095f', 'Groceries', 0);
INSERT INTO category (id, name, count) VALUES ('c2ae29e5-1d2f-4e6c-bc41-afc587401a06', 'Addictions', 0);
