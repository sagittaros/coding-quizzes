(ns coding-quizzes.bigsum
  (:require [clojure.core.reducers :as r]))

;; F(n) = (1^1 + 2^2 + 3^3 + ... + n^n) modulo 123456789 (1 <= n <= 50_000_000)

;; Modular arithmetic
;; (A + B) mod C = (A mod C + B mod C) mod C
;; (A * B) mod C = (A mod C * B mod C) mod C

(defn mod-mult [& args]
  (let [mod' #(mod % 123456789)]
    (reduce #(mod' (* %1 %2)) args)))

(defn exp [n & [base]]
  (let [base' (or base n)
        half (delay (exp n (quot base' 2)))]
    (cond
      (= base 0) 1
      (= base 1) n
      (= 1 (mod base' 2)) (mod-mult n @half @half)
      :else (mod-mult @half @half))))

(defn expsum [n]
  (let [mod' #(mod % 123456789)
        memoized-exp (memoize exp)]
    (reduce (fn [sum i]
              (->> i memoized-exp (+ sum) mod'))
            (range 1 (inc n)))))

(defn expsum-parallel [n]
  (let [mod' #(mod % 123456789)
        memoized-exp (memoize exp)
        inputs (vec (range 1 (inc n)))]
    (r/fold (comp mod' +) (r/map memoized-exp inputs))))

(comment
  (time (expsum 50000000)) ;; => 30733500, 725 seconds
  (time (expsum-parallel 50000000)) ;; => 30733500, 246 seconds
  (time (expsum 10000)) ;; => 65812574
  (time (expsum-parallel 10000)) ;; => 65812574

  ;; using reducers library
  (r/fold + (r/map exp (range 1 (inc 2))))

  (exp 3)
  (expsum 5))
