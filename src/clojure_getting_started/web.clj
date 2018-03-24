(ns clojure-getting-started.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :refer [wrap-params]]
            [environ.core :refer [env]]
            [clojure-getting-started.html :as html]))

(defroutes app-routes
  (GET "/" [reset]
       (html/front-page reset))
  (GET "/date" []
       {:body (str (java.util.Date.))})
  (POST "/reset" []
        (html/reset-database))
  (POST "/" [name]
        (html/input-name name))
  (GET "/names" []
       (html/list-names))
  (ANY "*" []
       (route/not-found (slurp (io/resource "404.html")))))

(def app (wrap-params app-routes))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
