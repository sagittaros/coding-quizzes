(ns coding-quizzes.arithmetic.divide
  (:require [clojure.string :as str]))

;; 8:22 am
;; Saturday, 26 February 2022 (GMT-8)
;; Time in Washington, USA

(defn divide [num q]
  (loop [rem (mod num q) ;; remainder
         decimals [] ;; to store intermediate decimal places before termination
         rs []] ;; using "recur?" as a register
    (let [x (quot num q) ;; number before decimal point
          x' (quot (* 10 rem) q) ;; result of decimal division
          rem' (mod (* 10 rem) q)] ;; decimal remainder
      (cond
        ;; recurrence detected
        (= (last rs) rem') (str x "." (str/join "" decimals) x' "^")
        ;; terminating condition, remainder is precise, although here it might produce 10/5=2.0 instead of 2
        (or (= 0 rem) (= 0 rem')) (str x "." (str/join "" decimals) x')
        ;; special case when it is not recurring, but it is too long
        ;; arbitrarily stop at 10 decimals
        (> (count rs) 8) (str x "." (str/join "" decimals) x')
        ;; continue to subdivide
        :else (recur (mod (* 10 rem) q)
                     (conj decimals x')
                     (conj rs rem'))))))

(comment
  (divide 10 4);; => "2.5"
  (divide 10 6);; => "1.6^"
  (divide 10 3);; => "3.3^"
  (divide 5557 100);; => "55.57"
  ;; )
  )

(comment
  (divide 10 2)
  (divide 66667 1000);; => "66.667"
  (divide 66667 235);; => "283.689361702127659574468085106382978723404255319148^"
  (/ 66667.0 235.0);; => 283.68936170212766
  (/ 162 235.0);; => 0.6893617021276596
  (divide 162 235);; => "0.689361702127659574468085106382978723404255319148^"
  (divide 10 4);; => "2.5"
  (divide 10 3))

;; FIXME, this has yet to support recurring sequence such as 8.333676767
