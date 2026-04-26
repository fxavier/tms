-- Enable UUID generation functions
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Enable trigram-based text search for partial matching (e.g., licence plate search)
CREATE EXTENSION IF NOT EXISTS "pg_trgm";
