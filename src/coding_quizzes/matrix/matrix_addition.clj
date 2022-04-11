(ns coding-quizzes.matrix.matrix-addition
  (:require
   [coding-quizzes.matrix.matrix-generation :refer [gen-ndim-matrix]]))

 ;; (defn- gen-unimatrix [dims]

 ;;  (mapv (fn [i]
 ;;          (mapv (fn [j] [i j])
 ;;                (range J))) (range I)))

(def ^:private m1
  [[0 1]
   [2 0]])

(def ^:private m2
  [[1 3]
   [4 1]])

(comment
  (println "===")
  (map vector (range 3) (range 3))
  (map (partial apply +) [[1 2] [3 4] [4 6]]))

(comment
  (gen-ndim-matrix 3)
  [[[[0 0 0] [1 0 0] [2 0 0]]
    [[0 1 0] [1 1 0] [2 1 0]]
    [[0 2 0] [1 2 0] [2 2 0]]]
   [[[0 0 1] [1 0 1] [2 0 1]]
    [[0 1 1] [1 1 1] [2 1 1]]
    [[0 2 1] [1 2 1] [2 2 1]]]
   [[[0 0 2] [1 0 2] [2 0 2]]
    [[0 1 2] [1 1 2] [2 1 2]]
    [[0 2 2] [1 2 2] [2 2 2]]]])
