(ns coding-quizzes.powerset)

(def inputs '("apple" "orange" "pear" "pineapple" "papaya"))

;; =======================================
;; Iterative approach
;; https://cs.stackexchange.com/questions/121230/explanation-of-on2n-time-complexity-for-powerset-generation
;; complexity = n(2^n)

(defn bad-naive-powerset
  "Find all combination (alternative)"
  [xs]
  (->> xs
       ((comp range count)) ;; convert to index
       (mapcat #(->> xs
                     (remove (partial = (nth xs %)))
                     bad-naive-powerset))
       (cons xs)
       #_distinct))

(defn binary-pattern-powerset
  "This method uses the intrinsic that from 0 to n, the entire binary sequence is included"
  [xs]
  (let [binary-vec (-> xs count to-binary)]
    binary-vec))

(comment
  (->> inputs count range)

  (-> inputs
      bad-naive-powerset
      count)
  (= (bad-naive-powerset inputs)
     '(["apple" "orange" "pear"]
       ["orange" "pear"]
       ["pear"]
       []
       ["orange"]
       ["apple" "pear"]
       ["apple"]
       ["apple" "orange"])))

;; =======================================
;; USE BACKTRACKING
;; complexity = n(2^n)
