(ns coding-quizzes.core
  (:gen-class))

(def matrix [[1 0 1 0 0]
             [1 0 1 1 1]
             [1 1 1 1 1]
             [1 0 0 1 0]])

(defn- get-coords [m]
  (if-not (sequential? m)
    (->> m
         (map-indexed vector)
         (filter #(= 1 (second %))))
    (map #(get-coords %) m)))

(comment
  (get-coords [1 2 3 0])
  (zipmap (range) [2 3 4 5])
  (map-indexed vector [2 3 4 5])

  (let [find-ones (fn [i v] (when (= 1 v) i))
        xf (comp (map #(map-indexed find-ones %))
                 (map #(remove nil? %)))
        indices (->> matrix
                     (into [] xf)
                     (map-indexed (fn [i v] (map #(vector i %) v)))
                     (mapcat identity))]
    indices))

(defn -main
  []
  (prn matrix))
