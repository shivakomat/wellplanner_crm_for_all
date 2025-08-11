# --- !Downs
DROP TABLE IF EXISTS "play_evolutions" CASCADE;
DROP TABLE IF EXISTS "teams" CASCADE;
DROP TABLE IF EXISTS "vendor_manage_categories" CASCADE;
DROP TABLE IF EXISTS "vendor_categories" CASCADE;
DROP TABLE IF EXISTS "vendor_manage" CASCADE;
DROP TABLE IF EXISTS "vendor_contacts_lists" CASCADE;
DROP TABLE IF EXISTS "vendor_buckets" CASCADE;
DROP TABLE IF EXISTS "budget_breakdowns" CASCADE;
DROP TABLE IF EXISTS "vendor_contacts" CASCADE;
DROP TABLE IF EXISTS "task_comments" CASCADE;
-- Remove FK constraint that links task_items to tasks before dropping tables
ALTER TABLE "task_items" DROP CONSTRAINT IF EXISTS CONSTRAINT_4D CASCADE;
DROP TABLE IF EXISTS "task_items" CASCADE;
DROP TABLE IF EXISTS "tasks" CASCADE;
DROP TABLE IF EXISTS "projects" CASCADE;
DROP TABLE IF EXISTS "clients" CASCADE;
DROP TABLE IF EXISTS "users" CASCADE;
DROP TABLE IF EXISTS "businesses" CASCADE;