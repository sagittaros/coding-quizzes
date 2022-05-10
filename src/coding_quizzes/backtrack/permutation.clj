(ns coding-quizzes.backtrack.permutation)

(defn permute [choices & [walked]]
  (prn "choices" choices "walked" walked)
  (if (empty? choices) ;; terminating condition
    [walked] ;; branch is complete
    (mapcat #(permute (filter (partial not= %) choices) ;; permute n-1 elements, join results
                      (conj (or walked []) %))
            choices))) ;; walking down the branch

(comment
  (permute [1 2 3])
  (str \a \b)
  (prn "====")
  (zipmap (range) "aaa")
  (->> "abb"
       (zipmap (range))
       permute
       (map (partial map second))
       (map (partial apply str))
       set))

;; =========================
;; Polkadot quiz
;; =========================

(defn permute' [choices & [walked]]
  (if (empty? choices)  ;; terminating condition
    [walked]  ;; branch is complete
    (->> choices ;; backtrack starts
         (mapcat #(permute' (filter (partial not= %) choices)  ;; permute n-1 elements, join results
                            (conj walked %))))))  ;; walk down the branch

(->> "input" ;; start threading
     (zipmap (range)) ;; index the string
     permute'  ;; run permute
     (map (partial map second)) ;; unpack char
     (map (partial apply str)) ;; char to str
     set ;; dedupe
     count)  ;; equivalent to O(n!)
