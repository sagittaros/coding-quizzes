(ns coding-quizzes.backtracking-permutation)

(defn permute [choices & [result walked]]
  (if (empty? choices)
    (conj result walked)
    (->> choices
         (mapcat #(permute (filter (partial not= %) choices)
                           result
                           (conj walked %)))
         (map vec)
         sort)))

(comment
  (permute [1 2 3]))
