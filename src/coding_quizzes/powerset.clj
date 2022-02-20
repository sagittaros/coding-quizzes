(ns coding-quizzes.powerset
  (:require [coding-quizzes.utils :refer [exp]]))

(def inputs '("apple" "orange" "pear"))

;; =======================================
;; Iterative approach
;; https://cs.stackexchange.com/questions/121230/explanation-of-on2n-time-complexity-for-powerset-generation
;; complexity = n(2^n)
;; =======================================

(defn binary-pattern-powerset
  ;; https://www.geeksforgeeks.org/power-set/
  "This method uses the intrinsic that from 0 to n, the entire binary sequence is included"
  [xs]
  (let [indexed (zipmap (range) xs)
        universe (->> xs count (exp 2))
        sets (for [possible (range universe)] ;; complexity = 2^n
               (for [[i x] indexed]           ;; complexity = n
                 ;; if the position (eg 2) of bit (eg 00100) overlaps with
                 ;; the bitsequence (eg 10100) of the number (20),
                 ;; include to powerset
                 (when (< 0 (bit-and possible
                                     (bit-shift-left 1 i)))
                   x)))]
    (map (partial remove nil?) sets)))

;; =======================================
;; Backtrack (recursive) approach
;; same complexity as iterative approach
;; =======================================

(defn powerset [choices]
  (letfn
   [(dive [parent i] ;; walked should be vector
      (let [with (conj parent (nth choices i))
            without parent
            i' (inc i)]
        (if (= i (->> choices count dec))
          [with without]
          (mapcat identity
                  [(dive with i')
                   (dive without i')]))))]
    (dive [] 0)))

(comment
  (-> inputs
      binary-pattern-powerset
      (#(vector (count %) %)))

  (binary-pattern-powerset [1 2 3])
  (powerset inputs)

  (assert (= (->> inputs
                  binary-pattern-powerset
                  (map vec)
                  sort)
             '([]
               ["apple"]
               ["orange"]
               ["pear"]
               ["apple" "orange"]
               ["apple" "pear"]
               ["orange" "pear"]
               ["apple" "orange" "pear"]))))

;; =======================================
;; TODO USE BACKTRACKING
;; complexity = n(2^n)
