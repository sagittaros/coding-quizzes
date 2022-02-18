(ns coding-quizzes.sorting)

(defn merge' [R [x & xrest :as X] [y & yrest :as Y]]
  (println "merge'" R "|" x xrest "|" y yrest)
  (if (and (not-empty X) (not-empty Y))
    (if (<= x y)
      (merge' (conj R x) xrest Y)
      (merge' (conj R y) X yrest))
    (concat R X Y)))

(defn merge-sort [X]
  (println "merge-sort" X)
  (if (> (count X) 1)
    (let [sides (split-at (/ (count X) 2) X)]
      (merge' []
              (merge-sort (get sides 0))
              (merge-sort (get sides 1))))
    X))

(comment
  (println "====== START =====")
  (merge-sort [0 3 1 2 2 8 4 6]))
