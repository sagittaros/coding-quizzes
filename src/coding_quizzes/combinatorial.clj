(ns coding-quizzes.combinatorial
  (:require [clojure.set :as set]))

(def inputs '("apple" "orange" "pear"))

(defn all-combination
  "Find all combination (alternative)"
  [xs]
  (->> xs
       ((comp range count))
       (mapcat #(->> xs
                     (remove (partial = (nth xs %)))
                     all-combination))
       (cons xs)
       distinct))

(defn all-combination-as-sets
  "Find all combination (using set union)"
  [xs]
  (apply set/union (set [xs])
         (for [i (range (count xs))]
           (->> xs
                (remove (partial = (nth xs i)))
                all-combination-as-sets))))

(comment
  (all-combination-as-sets inputs)
  (= (all-combination inputs)
     '(["apple" "orange" "pear"]
       ["orange" "pear"]
       ["pear"]
       []
       ["orange"]
       ["apple" "pear"]
       ["apple"]
       ["apple" "orange"])))
