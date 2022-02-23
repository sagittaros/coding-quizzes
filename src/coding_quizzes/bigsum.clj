(ns coding-quizzes.bigsum)

;; F(n) = (1^1 + 2^2 + 3^3 + ... + n^n) modulo 123456789 (1 <= n <= 50_000_000)

;; Modular arithmetic
;; (A + B) mod C = (A mod C + B mod C) mod C
;; (A * B) mod C = (A mod C * B mod C) mod C

(defn mod-mult [& args]
  (let [mod' #(mod % 123456789)]
    (loop [remaining args prod 1]
      (if (empty? remaining) prod
          (recur (rest remaining)
                 (mod' (* prod (first remaining))))))))

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
    (loop [i 1 sum 0]
      (if (> i n) sum
          (recur (inc i) (mod' (+ sum (mod' (memoized-exp i)))))))))

(comment
  (expsum 50000000)
  (expsum 10000)
  (exp 3)
  (expsum 10))
