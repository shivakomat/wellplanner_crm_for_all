# --- !Ups
CREATE TABLE "contacts" (
    "id"            SERIAL PRIMARY KEY,
    "business_id"   SERIAL,
    "name"          varchar(100),
    "phone"         varchar(40),
    "email"         varchar(100),
    "notes"         text,
    FOREIGN KEY ("business_id") REFERENCES "businesses"(id),
    "modified_date" NUMERIC,
    "created_date"  NUMERIC
);

# --- !Downs
DROP TABLE "contacts";
