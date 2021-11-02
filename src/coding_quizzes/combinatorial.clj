(ns coding-quizzes.combinatorial
  (:require [clojure.set :as set]))

(def inputs '("apple" "orange" "pear"))

(defn all-combination
  "Find all combination (using set union)"
  [xs]
  (apply set/union (set [xs])
         (for [i (range (count xs))]
           (->> xs
                (remove (partial = (nth xs i)))
                all-combination))))

(defn all-combination-alt
  "Find all combination (alternative)"
  [xs]
  (->> (->> xs
            (remove (partial = (nth xs i)))
            all-combination-alt)
       (for [i (range (count xs))])
       (mapcat identity)
       (apply conj [xs])
       distinct))

(comment
  (all-combination inputs)
  (all-combination-alt inputs)

  '(["apple" "orange" "pear"]
    ["orange" "pear"]
    ["pear"]
    []
    ["orange"]
    ["apple" "pear"]
    ["apple"]
    ["apple" "orange"]))
