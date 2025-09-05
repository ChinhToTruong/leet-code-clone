-- Tạo thêm database khác ngoài POSTGRES_DB
DO
$$
BEGIN
  IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'my_database') THEN
    CREATE DATABASE my_database OWNER postgres ENCODING 'UTF8' TEMPLATE template1;
END IF;
END;
$$;