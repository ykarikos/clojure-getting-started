(ns clojure-getting-started.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
            [clojure-getting-started.db :as db]))

(defn splash []
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello world!"})

(def db
  (env :database-url))

(defn name-count []
  {:body (str "name count: "
              (:count (db/name-count db)))})

(defroutes app
  (GET "/" []
       (splash))
  (GET "/name-count" []
       (name-count))
  (ANY "*" []
       (route/not-found (slurp (io/resource "404.html")))))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))
