(ns coding-quizzes.utils)

(defn naive-exp [base power]
  (reduce * (repeat power base)))

;; (pow 2 4)
;; = (* (pow 2 2) (pow 2 2))
;; = (* (* (pow 2 1) (pow 2 1))
;;      (* (pow 2 1) (pow 2 1)))

(defn binary-exp [base power]
  (cond
    (= power 1) base
    (= (mod power 2) 1) (* base (binary-exp base (dec power)))
    :else (* (binary-exp base (quot power 2))
             (binary-exp base (quot power 2)))))

(def exp binary-exp)

(defn to-binary
  ;; https://stackoverflow.com/questions/57334956/why-reverse-the-remainder-during-decimal-to-binary-conversion
  ;; keep dividing by 2 until quotient becomes 0.
  ;; The intuition is that the least significant bit get truncated (shifted right)
  ;; 13 => [[6 1] [3 0] [1 1] [0 1]]
  ;; where [x y] = [quotient remainder]
  [n]
  (loop [quotient n
         remainders '()]
    (if (> quotient 0)
      (recur (quot quotient 2)
             (conj remainders (mod quotient 2)))
      remainders)))

(defn factorial [n]
  (loop [i 1
         acc 1]
    (if (= i n)
      (* acc n)
      (recur (inc i) (* acc i)))))

(comment
  (factorial 10)
  (to-binary 13)
  (to-binary 9)
  (naive-exp 2 80)
  (binary-exp 2 80))

;; loop/recur is useful for divergent computation (1 to many)
;; corecursion is useful for series induction
;; reduce is useful to convergent computation (many to 1)
