(defproject clojure-getting-started "1.0.0-SNAPSHOT"
  :description "Demo Clojure web app"
  :url "http://clojure-getting-started.herokuapp.com"
  :license {:name "Eclipse Public License v1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.4.0"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [environ "1.0.0"]
                 [com.layerware/hugsql "0.4.8"]
                 [org.clojure/tools.reader "1.2.1"]
                 [org.postgresql/postgresql "42.2.1"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler clojure-getting-started.web/app}
  :profiles
   {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]]}})
