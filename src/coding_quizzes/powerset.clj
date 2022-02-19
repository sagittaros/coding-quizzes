(ns coding-quizzes.powerset
  (:require [coding-quizzes.utils :refer [exp]]))

(def inputs '("apple" "orange" "pear"))

;; =======================================
;; Iterative approach
;; https://cs.stackexchange.com/questions/121230/explanation-of-on2n-time-complexity-for-powerset-generation
;; complexity = n(2^n)

(defn binary-pattern-powerset
  ;; https://www.geeksforgeeks.org/power-set/
  "This method uses the intrinsic that from 0 to n, the entire binary sequence is included"
  [xs]
  (let [indexed (zipmap (range) xs)
        universe (->> xs count (exp 2))
        sets (for [possible (range universe)] ;; complexity = 2^n
               (for [[i x] indexed]           ;; complexity = n
                 (when (< 0 (bit-and possible
                                     (bit-shift-left 1 i)))
                   x)))]
    (map (partial remove nil?) sets)))

(comment
  (-> inputs binary-pattern-powerset))

;; =======================================
;; USE BACKTRACKING
;; complexity = n(2^n)
