(ns clojure-getting-started.html
  (:require [environ.core :refer [env]]
            [clojure-getting-started.db :as db]
            [hiccup.core :as h]))

(defn response [body]
  {:status 200
   :headers {"Content-Type" "text/html; charset=utf-8"}
   :body body})


(def db-uri
  (env :database-url))

(def links
  (h/html
   [:p
    [:a {:href "/names"}
     "List names"]]
   [:p
    [:a {:href "/date"}
     "Current date and time"]]
   [:p
    [:a {:href "/"}
     "Front page"]]))


(def reset-form
  (h/html
   [:form {:method "POST" :action "/reset"}
    [:input {:type "submit" :value "Reset database"}]]))

(defn front-page [reset]
  (response
   (h/html
     [:h1 "λ Clojure & PostgreSQL PaaS test app"]
     [:form {:method "POST"}
      [:p "Hello "
       [:input {:name "name"}]
       "! "
       [:input {:type "submit"
                :value "OK"}]]]
     links
     (when reset reset-form))))

(defn reset-database []
  (println "Dropping names table...")
  (db/drop-names-table db-uri)
  (println "Creating names table...")
  (db/create-names-table db-uri)
  (response
   (h/html
    [:p "Database reset"]
    links)))

(defn input-name [name]
  (db/insert-name db-uri {:name name})
  (response
   (h/html
    [:p "Hello " name "!"]
    links)))

(defn get-names []
  (->> db-uri
       (db/all-names)
       (map :name)))

(defn list-names []
  (let [name-count (-> db-uri (db/name-count) :count)]
    (response
     (h/html
       (if (> name-count 0)
         [:div
          [:p "Name count: " name-count]
          [:p "All names:"]
          [:ul
           (for [name (get-names)]
             [:li name])]]
         [:p "No names"])
       links))))
