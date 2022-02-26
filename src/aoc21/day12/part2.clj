(ns aoc21.day12.part2
  (:require [clojure.string :as str]))

(def paths "start-A
start-b
A-c
A-b
b-d
A-end
b-end")

(def paths2 "re-js
qx-CG
start-js
start-bj
qx-ak
js-bj
ak-re
CG-ak
js-CG
bj-re
ak-lg
lg-CG
qx-re
WP-ak
WP-end
re-lg
end-ak
WP-re
bj-CG
qx-start
bj-WP
JG-lg
end-lg
lg-iw")

(defn ->adjacency-list [input]
  (->> (str/split-lines input)
       (map #(str/split % #"-"))
       (reduce (fn [acc [s e]]
                 (merge-with concat acc {s [e] e [s]}))
               {})))

(defn uppercase? [s]
  (= (str/upper-case s) s))

(defn allow? [visited node]
  ;; (prn "visited" visited "node" node)
  (let [visited-count (or (visited (str/lower-case node)) 0)]
    (println "node" node "count" visited-count "allow?" (cond
                                                          (= "start" node) false
                                                          (= "end" node) false
                                                          (uppercase? node) true
                                                          :else (< visited-count 3)))
    (cond
      (= "start" node) false
      (= "end" node) false
      (uppercase? node) true
      :else (< visited-count 2))))

(defn get-paths [g node visited path]
  (let [path' (conj path node)
        more (g node)
        visited' (merge-with + visited {node 1})
        q (filter (partial allow? visited') more)]
    ;; (prn {"node" node "path" path' "visited" visited'})
    (if (= "end" node) [path']
        (mapcat #(get-paths g % visited' path') q))))

;; ()

(comment
  (->adjacency-list paths)
  (-> paths
      ->adjacency-list
      (get-paths "start" {} [])))
