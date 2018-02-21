(ns clojure-getting-started.html
  (:require [environ.core :refer [env]]
            [clojure-getting-started.db :as db]
            [hiccup.core :as h]))

(def db-uri
  (env :database-url))

(def links
  (h/html
   [:p
    [:a {:href "/names"}
     "List names"]]
   [:p
    [:a {:href "/"}
     "Front page"]]
   [:form {:method "POST" :action "/reset"}
    [:input {:type "submit" :value "Reset database"}]]))

(defn front-page []
  {:body
   (h/html
     [:form {:method "POST"}
      [:p "Hello "
       [:input {:name "name"}]
       "! "
       [:input {:type "submit"
                :value "OK"}]]]
     links)})

(defn reset-database []
  (println "Dropping names table...")
  (db/drop-names-table db-uri)
  (println "Creating names table...")
  (db/create-names-table db-uri)
  {:body
   (h/html
    [:p "Database reset"]
    links)})

(defn input-name [name]
  (db/insert-name db-uri {:name name})
  {:body
   (h/html
    [:p "Hello " name "!"]
    links)})

(defn get-names []
  (->> db-uri
       (db/all-names)
       (map :name)))

(defn list-names []
  (let [name-count (-> db-uri (db/name-count) :count)]
    {:body
     (h/html
       (if (> name-count 0)
         [:div
          [:p "Name count: " name-count]
          [:p "All names:"]
          [:ul
           (for [name (get-names)]
             [:li name])]]
         [:p "No names"])
       links)}))
