-- Projects Schema

# --- !Ups
CREATE TABLE "projects" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(255) NOT NULL,
  "budget" DOUBLE PRECISION,
  "notes" TEXT,
  "client_id" INTEGER NOT NULL,
  "business_id" INTEGER NOT NULL,
  "modified_date" NUMERIC,
  "created_date" NUMERIC,
  "is_deleted" BOOLEAN DEFAULT FALSE,
  FOREIGN KEY ("business_id") REFERENCES "businesses"(id),
  FOREIGN KEY ("client_id") REFERENCES "clients"(id)
);


-- insert into "projects" (id, name, event_type, brides_name, grooms_name, budget, event_date, client_id, business_id, modified_date, created_date) values (1, 'Radhika & Shiva', 'WEDDING', 'Radhika', 'Shiv', 4000, 20102020, 1, 1, 10102020, 10102020, false);

# --- !Downs
DROP TABLE "projects";