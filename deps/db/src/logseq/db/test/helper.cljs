(ns ^:node-only logseq.db.test.helper
  "Main ns for providing test fns for DB graphs"
  (:require [datascript.core :as d]
            [logseq.db.sqlite.build :as sqlite-build]
            [logseq.db.sqlite.create-graph :as sqlite-create-graph]
            [logseq.db.frontend.schema :as db-schema]))

(defn create-conn
  "Create a conn for a DB graph seeded with initial data"
  []
  (let [conn (d/create-conn db-schema/schema-for-db-based-graph)
        _ (d/transact! conn (sqlite-create-graph/build-db-initial-data "{}"))]
    conn))

(defn create-conn-with-blocks
  "Create a conn with create-db-conn and then create blocks using sqlite-build"
  [opts]
  (let [conn (create-conn)
        _ (sqlite-build/create-blocks conn opts)]
    conn))