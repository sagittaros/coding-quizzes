(ns coding-quizzes.matrix.matrix-generation
  (:require
   [clojure.string :as str]))

(defn gen-ndim-matrix [n & [f idxs]]
  (if (= n (count idxs))
    ((or f #(-> % reverse vec)) idxs)
    (mapv #(gen-ndim-matrix n f (conj idxs %)) (range n))))

;; (defn- gen3d
;;   "naive method"
;;   []
;;   (mapv (fn [i]
;;           (mapv (fn [j]
;;                   (mapv (fn [k]
;;                           [i j k])
;;                         (range 3))) (range 3))) (range 3)))

(comment
  (println "===")
  (gen-ndim-matrix 2)
  (gen-ndim-matrix 3 (partial str/join ":"))
  (gen-ndim-matrix 3 (partial apply +))
  ;; (gen3d)
  (map vector (range 3) (range 3))
  (map (partial apply +) [[1 2] [3 4] [4 6]]))
