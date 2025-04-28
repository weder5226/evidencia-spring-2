-- Password = 123456
INSERT INTO supervisors (name, email, password, role, is_enabled, version)
SELECT 'Admin', 'admin@example.com', '$2a$10$Y0C1q4wkqLNaMczw7lahBeOSA92n6M9ED4mwTfW96a8gPSkUgEaFu', 'ADMIN', true, 0
WHERE NOT EXISTS (
  SELECT 1 FROM supervisors WHERE email = 'admin@example.com'
);