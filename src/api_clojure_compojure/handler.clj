(ns api-clojure-compojure.handler
  (:require [compojure.api.sweet :refer :all]
            [api-clojure-compojure.services :refer :all]
            [ring.util.http-response :refer :all]))

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Reto API Rest con Clojure (utilizando compojure-api)"
                    :description "Compojure CRUD"}
             :tags [{:name "api", :description "some apis"}]}}}
        
    (context "/user" []
      :tags ["user"]
      
      (GET "/" []
        :return ResponseList
        :summary "Obtener todos los usuarios"
        (ok (getUsers)))
        
      (GET "/:id" []
        :return ResponseData
        :path-params [id :- String]
        :summary "Obtener usuario por ID"
        (ok (getUser id)))
        
      (POST "/" []
        :return ResponseData
        :body [user User]
        :summary "Agregar usuario"
        (ok (addUser! user)))
        
      (PUT "/" []
        :return ResponseData
        :body [user User]
        :summary "Actualizar usuario"
        (ok (updateUser! user)))
        
      (DELETE "/:id" []
        :return ResponseMessage
        :path-params [id :- String]
        :summary "Eliminar usuario por ID"
        (ok (deleteUser! id))))))
