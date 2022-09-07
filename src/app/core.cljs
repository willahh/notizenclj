(ns app.core
  "This namespace contains your application and is the entrypoint for 'yarn start'."
  (:require [reagent.dom :as rdom]
            [datascript.core :as d]
            [datascript.db :as db]
            [app.hello :refer [hello]]))

(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (rdom/render [hello] (.getElementById js/document "app")))

(defn ^:export main
  "Run application startup logic."
  []
  (render))

(comment

  (do
    ;; define schema
    (def schema {:aka {:db/cardinality :db.cardinality/many}})

    ;; populate db with initial datoms
    (def datoms #{(d/datom 1 :age 17)
                  (d/datom 1 :name "User")})



    (def initial-data [[:db/add 1 :name "Petr"]
                       [:db/add 1 :age 44]
                       [:db/add 2 :name "Ivan"]
                       [:db/add 2 :age 25]])
    (def test-db
      (-> (d/empty-db)
          (d/db-with initial-data)))

    (def conn (d/create-conn schema))


    (d/q '[:find ?e
           :where [?e :name]] test-db)

    )


  )