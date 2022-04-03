(ns coding-quizzes.num-sequence)

(defn smallest-free [xs]
  (let [in-xs? (partial contains? (zipmap xs (repeat 1)))]
    (->> xs
         (map inc)
         (remove in-xs?)
         (apply min))))

(comment
  (assert (= -99 (smallest-free [10 2 -100 73 10000 3300])))
  (assert (= 4 (smallest-free [10 3 8 73 10000 3300])))
  (assert (= 35 (smallest-free [34 36 32 33])))
  (assert (= 15 (smallest-free [8 23 9 0 12 11 1 10 13 7 41 4 14 21 5 17 3 19 2 6]))))

