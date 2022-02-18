(ns coding-quizzes.recursion)

(defn slow-fib [i] ;; O(2^n)
  (if (<= i 1) i
      (+ (slow-fib (- i 1))
         (slow-fib (- i 2)))))

(defn slow-factorial! [n]
  (if (= n 1) 1
      (* n (slow-factorial! (dec n)))))

(defn fib-recur [n]
  (loop [i 1
         seq' [0 1]]
    (let [sum (apply + (take-last 2 seq'))]
      (cond
        (< i n) (recur (inc i) (conj seq' sum))
        :else (conj seq' sum)))))

;; 0 1 1 2 3 5
(def fib (lazy-cat [0 1] (map + fib (rest fib))))

(comment
  (slow-fib 4)
  (slow-factorial! 4)

  (map + [0 1] [1])
  (fib-recur 20)
  (take 20 fib))

;; http://squirrel.pl/blog/2010/07/26/corecursion-in-clojure/
