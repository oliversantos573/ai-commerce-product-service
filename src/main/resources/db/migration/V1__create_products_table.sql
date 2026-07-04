CREATE TABLE products (

                          id UUID PRIMARY KEY,

                          sku VARCHAR(50) NOT NULL UNIQUE,

                          name VARCHAR(150) NOT NULL,

                          description VARCHAR(1000) NOT NULL,

                          brand_id UUID NOT NULL,

                          category_id UUID NOT NULL,

                          status VARCHAR(20) NOT NULL,

                          created_at TIMESTAMP NOT NULL,

                          updated_at TIMESTAMP NOT NULL

);

CREATE INDEX idx_products_sku
    ON products(sku);

CREATE INDEX idx_products_brand
    ON products(brand_id);

CREATE INDEX idx_products_category
    ON products(category_id);

CREATE INDEX idx_products_status
    ON products(status);