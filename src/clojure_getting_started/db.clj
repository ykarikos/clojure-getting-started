(ns clojure-getting-started.db
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "sql/names.sql")
