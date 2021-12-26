(ns api-clojure-compojure.services
  (:require [schema.core :as s]
            [api-clojure-compojure.helpers :as helpers]
            [ring.swagger.schema :refer [coerce!]]))

;; Schema User
(s/defschema User {(s/optional-key :id) String
                   :name String
                   :email #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}"})

(s/defschema ResponseMessage {:ok Boolean
                      :message String})

(s/defschema ResponseData {:ok Boolean
                      (s/optional-key :message) String
                      (s/optional-key :data) User})

(s/defschema ResponseList {:ok Boolean
                      (s/optional-key :count) Integer
                      (s/optional-key :message) String
                      (s/optional-key :data) [User]})

(defonce users (atom (array-map)))

(defn get-user [id] (@users id))

(defn getUser [id]
  (let [user (get-user id)]
    (if user {:ok true :data user} {:ok false :message "No se encontró al usuario"})))

(defn get-users [] (-> users deref vals reverse))

(defn getUsers []
  (let [users (get-users)]
    (if (> (count users) 0) {:ok true :count (count users) :data users} {:ok false :message "No se encontraron resultados"})))

(defn deleteUser! [id]
  (let [user (get-user id)]
  (swap! users dissoc id)
  {:ok (if user true false)
   :message (if user "El usuario fue eliminado" "No se pudo eliminar al usuario ")}))

(defn addUser! [user]
  (let [id (helpers/uuid)
      newUser (coerce! User (assoc user :id id))]
  (swap! users assoc id newUser)
  {:ok true :data newUser}))

(defn updateUser! [user]
  (let [newUser (coerce! User user)]
  (swap! users assoc (:id user) newUser)
  {:ok true :data (get-user (:id user))}))

;; Initial data
(when (empty? @users)
  (addUser! {:name "Edder Silva" :email "arskang@gmail.com"})
  (addUser! {:name "Buza Caperuza" :email "arskang+001@gmail.com"}))
