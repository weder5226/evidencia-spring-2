CREATE TABLE IF NOT EXISTS clients (
  id           int4 GENERATED ALWAYS AS IDENTITY,
  first_name   varchar(100) NOT NULL,
  last_name    varchar(100) NOT NULL,
  id_number    varchar(20) NOT NULL UNIQUE,
  phone_number varchar(30) NOT NULL,
  city         varchar(50),
  address      varchar(80),
  email        varchar(80),
  version      int2 DEFAULT 0 NOT NULL,
  CONSTRAINT clients_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products (
  id             int4 GENERATED ALWAYS AS IDENTITY,
  name           varchar(50) NOT NULL UNIQUE,
  quantity_stock int4 NOT NULL,
  version        int2 DEFAULT 0 NOT NULL,
  CONSTRAINT products_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product_details (
  product_id  int4,
  image_url   varchar(500) NOT NULL,
  image_alt   varchar(100) NOT NULL,
  image_url_2 varchar(500) NOT NULL,
  image_alt_2 varchar(100) NOT NULL,
  image_url_3 varchar(500) NOT NULL,
  image_alt_3 varchar(100) NOT NULL,
  detail      varchar(250) NOT NULL,
  description varchar(700) NOT NULL,
  ingredients varchar(200) NOT NULL,
  category    varchar(80) NOT NULL,
  price       numeric(10, 2) NOT NULL,
  is_hidden   bool NOT NULL,
  CONSTRAINT product_details_pkey PRIMARY KEY (product_id),
  CONSTRAINT product_details_to_products_fkey FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE IF NOT EXISTS sales (
  id            int4 GENERATED ALWAYS AS IDENTITY,
  sale_date     date DEFAULT CURRENT_DATE NOT NULL,
  delivery_date date,
  client_id     int4 NOT NULL,
  version       int2 DEFAULT 0 NOT NULL,
  CONSTRAINT sales_pkey PRIMARY KEY (id),
  CONSTRAINT sales_to_clients_fkey FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE IF NOT EXISTS sale_details (
  sale_id         int4,
  product_id      int4,
  unit_price_sold numeric(10, 2) NOT NULL,
  quantity_sold   int4 NOT NULL,
  version         int2 DEFAULT 0 NOT NULL,
  CONSTRAINT sale_details_pkey PRIMARY KEY (sale_id, product_id),
  CONSTRAINT sale_details_to_products_fkey FOREIGN KEY (product_id) REFERENCES products (id),
  CONSTRAINT sale_details_to_sales_fkey FOREIGN KEY (sale_id) REFERENCES sales (id)
);

CREATE TABLE IF NOT EXISTS supervisors (
  id         int4 GENERATED ALWAYS AS IDENTITY,
  name       varchar(100) NOT NULL,
  email      varchar(80) NOT NULL UNIQUE,
  password   varchar(100) NOT NULL,
  role       varchar(30) NOT NULL,
  is_enabled bool NOT NULL,
  version    int2 DEFAULT 0 NOT NULL,
  CONSTRAINT supervisors_pkey PRIMARY KEY (id)
);
