(ns coding-quizzes.graphs.djiskstra
  (:require [clojure.data.priority-map :refer [priority-map]]))

;; Djikstra's shortest path problem

;; :a -> :b (2)
;; :a -> :c (4)
;; :b -> :d (3)
;; :c -> :d (5)

;; shortest path is a -> b -> d == 2 + 3

(def graph
  {:a {:b 2
       :c 4}
   :b {:d 3}
   :c {:d 5}})

(defn vals-map [m f]
  (into {} (for [[k v] m] [k (f v)])))

(defn remove-keys [m m2]
  ;; map can be used as function, and since it is a function, it can be complemented
  (select-keys m (filter (complement m2) (keys m))))

(defn dijkstra [g start]
  (println)
  (loop [q (priority-map start 0) r {}] ;; q = queue, r = result
    (let [[v d] (peek q) ;; v = vertex, d = distance
          succ (g v) ;; successors of v
          unvisited (remove-keys succ r)
          distance (vals-map unvisited (partial + d))]
      (println "v" v
               "d" d
               "r" r
               "unvisited" unvisited
               "distance" distance)
      (if v
        (recur (merge-with min (pop q) distance)
               (assoc r v d))
        r))))

(comment
  (dijkstra graph :a))
