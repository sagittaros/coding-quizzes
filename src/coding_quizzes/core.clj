(ns coding-quizzes.core
  (:gen-class))

(def matrix [[1 0 1 0 0]
             [1 0 1 1 1]
             [1 1 1 1 1]
             [1 0 0 1 0]])

(defn- matrix->indices [m]
  (for [[i vals] (map-indexed vector m)
        [j val] (map-indexed vector vals)
        :when (= 1 val)]
    [i j]))

(defn- matrix->indices2 [m]
  (let [find-ones (fn [i v] (when (= 1 v) i))
        xf (comp (map #(map-indexed find-ones %))
                 (map #(remove nil? %)))
        indices (->> m
                     (into [] xf)
                     (map-indexed (fn [i v] (map #(vector i %) v)))
                     (mapcat identity))]
    indices))

(comment
  (zipmap (range) [2 3 4 5])
  (map-indexed vector [2 3 4 5])

  ;; matrix to index strategy 1
  (matrix->indices matrix)
  (matrix->indices2 matrix))

(comment
  '(([0 0] [0 2])
    ([1 0] [1 2] [1 3] [1 4])
    ([2 0] [2 1] [2 2] [2 3] [2 4])
    ([3 0] [3 3]))

  '([0 0] [0 2] [1 0] [1 2] [1 3] [1 4] [2 0] [2 1] [2 2] [2 3] [2 4] [3 0] [3 3])

  '([0 0 1 1 1 1 2 2 2 2 2 3 3] [0 2 0 2 3 4 0 1 2 3 4 0 3]))

(defn -main
  []
  (prn matrix))
