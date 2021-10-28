(ns coding-quizzes.largest-rect
  (:require
   [clojure.set :as set]))

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

(defn- gen-pairs [s]
  (for [[x1 y1 :as v1] s
        [x2 y2 :as v2] s
        :when (and (<= x1 x2) (<= y1 y2))]
    [v1 v2]))

(defn- gen-rectangle [pair]
  (let [[[ax ay] [bx by]] pair]
    (for [x (range ax (inc bx))
          y (range ay (inc by))]
      [x y])))

(comment
  ;; index visualization
  '(([0 0] [0 2])
    ([1 0] [1 2] [1 3] [1 4])
    ([2 0] [2 1] [2 2] [2 3] [2 4])
    ([3 0] [3 3]))

  ;; flattened index
  '([0 0] [0 2] [1 0] [1 2] [1 3] [1 4] [2 0] [2 1] [2 2] [2 3] [2 4] [3 0] [3 3]))

(defn find-largest-rect [& [input]]
;; strategy:
;; - convert indices into set (https://clojuredocs.org/clojure.core/set)
;; - generate pairs of 1s from the set of 1s (each pair represent the top-left and bottom-right corner of the rectangle)
;; - foreach pair, check if it fulfills rectangle criteria (all cells are one)
;; - each cell should compute a max value which is the max of (max(x), max(y), max(area))
;; - global max value is then computed out of all cells
  (let [matrix (or input matrix)
        ones (matrix->indices matrix)
        ones' (set ones)
        pairs (gen-pairs ones)
        is-rect? #(set/subset? (set %) ones')
        xf (comp (map gen-rectangle)
                 (filter is-rect?)
                 (map count))]
    (->> pairs
         (into [] xf)
         (apply max))))

(comment
  (zipmap (range) [2 3 4 5])
  (map-indexed vector [2 3 4 5])
  (= (matrix->indices matrix)
     (matrix->indices2 matrix))
  (-> matrix
      matrix->indices
      gen-pairs
      count)
  (find-largest-rect matrix))
