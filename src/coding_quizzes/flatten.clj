(ns coding-quizzes.flatten
  (:require [clojure.string :as str]))

(def nested
  {"a" {"b" {"c" 3}}
   "d" {"e" 4}
   "f" 5})

(defn join-keys [m & [ks]]
  (for [[k v] m]
    (let [ks' (if ks (conj ks k) [k])]
      (if (map? v)
        (mapcat identity (join-keys v ks'))
        [ks' v]))))

(defn vec->str [m]
  (for [[k v] m]
    [(str/join "." k) v]))

(comment
  (->> nested
       join-keys
       vec->str
       (into (hash-map))))
