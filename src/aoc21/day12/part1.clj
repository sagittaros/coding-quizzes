(ns aoc21.day12.part1
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

(defn allow? [visited node]
  (not (visited (str/lower-case node))))

(defn get-paths [g node visited path]
  (let [path' (conj path node)
        more (g node)
        visited' (conj visited node)
        q (filter (partial allow? visited') more)]
    ;; (prn {"node" node "next" (g node) "path" path' "q" q})
    (if (= "end" node) [path']
        (mapcat #(get-paths g % visited' path') q))))

;; ()

(comment
  (->adjacency-list paths)
  (println)
  (-> paths
      ->adjacency-list
      (get-paths "start" #{} []))
  (-> paths2
      ->adjacency-list
      (get-paths "start" #{} [])
      count))