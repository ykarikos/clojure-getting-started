
-- :name create-names-table
-- :command :execute
-- :result :raw
-- :doc Create names table
CREATE TABLE names (
     id    SERIAL PRIMARY KEY,
     created TIMESTAMP NOT NULL DEFAULT NOW(),
     name   VARCHAR(40) NOT NULL CHECK (name <> '')
);

-- :name drop-names-table :!
-- :doc Drop names table if exists
drop table if exists names

-- :name insert-name :! :n
-- :doc Insert a name returning affected row count
insert into names (name) values (:name)

-- :name name-count :? :1
-- :doc Get name count
select count(*) from names

-- :name all-names :? :*
-- :doc Get all names
select * from names order by id
