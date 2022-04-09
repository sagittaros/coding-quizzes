(ns coding-quizzes.linked-list)

(defn num->list [^Integer n]
  (->> (str n)
       (map (comp read-string str))
       (reduce conj '())))

;; a and b are lists
(defn sum [a b]
  (let [[a b] (map num->list [a b])] ;; extra, can also use '(a b c) directly
    (loop [a' a, b' b, carry 0
           res '()]
      (let [a! (first a'), b! (first b')
            sum' (reduce + (map #(or % 0) [a! b! carry]))
            [r! carry'] [(rem sum' 10) (quot sum' 10)]]
        (if (and (nil? a!) (nil? b!) (zero? carry)) (reverse res)
            (recur (rest a')
                   (rest b')
                   carry'
                   (conj res r!)))))))

(comment
  (sum 342 465)
  (sum 9999999 9999)
  (sum 99 99)
  (num->list 342)
  (num->list 807))
