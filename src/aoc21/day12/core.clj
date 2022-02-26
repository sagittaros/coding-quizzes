(ns aoc21.day12.core
  (:require [clojure.string :as str]))

(def paths "start-A
start-b
A-c
A-b
b-d
A-end
b-end")

(defn ->adjacency-list [input]
  (->> (str/split-lines paths)
       (map #(str/split % #"-"))
       (reduce (fn [acc [s e]]
                 (merge-with concat acc {(keyword s) [(keyword e)]
                                         (keyword e) [(keyword s)]}))
               {})))

(defn traverse [g])

(comment (->adjacency-list paths))
