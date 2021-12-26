(ns api-clojure-compojure.helpers)

(defn uuid [] (.toString (java.util.UUID/randomUUID)))
