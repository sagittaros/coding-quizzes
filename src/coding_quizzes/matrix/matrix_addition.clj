(ns coding-quizzes.matrix.matrix-addition
  (:require
   [coding-quizzes.matrix.matrix-generation :refer [gen-ndim-matrix]]))

(defn traverse [outer & [f idxs]]
  (if-not (vector? outer)
    ((or f list) (->> idxs reverse vec) outer)
    (mapv (fn [[i inner]]
            (traverse inner f (conj idxs i)))
          (zipmap (range) outer))))

(defn add-matrix [M1 M2]
  (let [add-with (fn [A idxs x] (+ x (get-in A idxs)))]
    (traverse M1 (partial add-with M2))))

(comment
  (let [M1 (gen-ndim-matrix 4 (partial apply +))
        M2 (gen-ndim-matrix 4 (partial apply *))]
    (add-matrix M1 M2))

  (println "===")
  (traverse (gen-ndim-matrix 3 (partial apply +)))
  (map vector (range 3) (range 3))
  (map (partial apply +) [[1 2] [3 4] [4 6]]))
