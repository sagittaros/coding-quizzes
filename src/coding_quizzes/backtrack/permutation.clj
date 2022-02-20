(ns coding-quizzes.backtrack.permutation)

(defn permute [choices & [result walked]]
  (if (empty? choices)
    (conj result walked)
    (->> choices
         (mapcat #(permute (filter (partial not= %) choices)
                           result
                           (conj (or walked []) %))))))

(comment
  (permute [1 2 3 4]))
