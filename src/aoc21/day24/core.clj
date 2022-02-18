(ns aoc21.day24.core
  (:require
   [clojure.edn :as edn]
   [clojure.string :as str]))

(defn parse-last? [xs]
  (let [v (-> xs last edn/read-string)]
    (if (integer? v)
      (conj (drop-last xs) v)
      xs)))

(def input (as-> "src/aoc21/day24/input.txt" v
             (slurp v)
             (str/split v #"\n")
             (map #(str/split % #"\s") v)
             (map parse-last? v)))

(comment
  input)
