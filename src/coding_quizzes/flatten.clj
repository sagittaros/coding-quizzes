(ns coding-quizzes.flatten
  (:require [clojure.string :as str]))

;; =============================================================
;; Combine nested keys
;; =============================================================

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

(defn join-keys-alt [m & [ks]]
  (map (fn [[k v]]
         (let [ks (if ks (conj ks k) [k])]
           (cond
             (map? v) (mapcat identity (join-keys v ks))
             :else [ks v])))
       m))

(defn vec->str [m]
  (for [[k v] m]
    [(str/join "." k) v]))

(comment
  (join-keys-alt nested)
  (->> nested
       join-keys-alt
       vec->str
       (into (hash-map))))

(comment
  (= (tree-seq map? identity nested)
     (tree-seq map? rest nested))
  (->> nested
       (tree-seq seq? identity)
       (map vector)))

;; =============================================================
;; Simply flatten nested structure
;; =============================================================

(comment
  ;; https://rmulhol.github.io/clojure/2015/05/12/flatten-tree-seq.html
  ;; https://github.com/clojure/clojure/blob/7529bc90a35eba940581311d7dfed21fec22b4f5/src/clj/clojure/core.clj#L7141
  (->> [:a [:b [:c :d] :e]]
       (tree-seq sequential? seq)
       (filter (complement sequential?))))
