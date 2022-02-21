(ns coding-quizzes.tree-bfs)

;     1-------
;    / \      \
;   2   3     9
;      / \   / \
;     4   5 8  28
;    /
;   66

(def tree [1 [2] [3 [4 [66]] [5]] [9 [8] [28]]])

;; the queue is implemented as some form of recursive listo
;; [list [list [list [list]]]], and concatenated at every step
(defn bfs [graph]
  (letfn [(bfs-queue [& graphs]
            (when graphs ;; terminate if nothing left to add
              (concat graphs
                      (->> graphs
                           (mapcat rest)
                           (apply bfs-queue)))))]
    (->> graph
         bfs-queue
         (map first))))

(comment
  tree
  (bfs tree))
