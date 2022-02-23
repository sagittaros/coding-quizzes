(ns coding-quizzes.tree-bfs)

;     1-------
;    / \      \
;   2   3     9
;      / \   / \
;     4   5 8  28
;    /
;   66

(def tree [1 [2] [3 [4 [66]] [5]] [9 [8] [28]]])

(def tree-aj {1 [2 3 9]
              3 [4 5]
              4 [66]
              9 [8 28]})

;; the queue is implemented as some form of recursive listo
;; [list [list [list [list]]]], and concatenated at every step
(defn bfs [graph]
  (letfn [(bfs-queue [& graphs]
            (when graphs ;; terminate if nothing left to add
              (->> graphs
                   (mapcat rest)
                   (apply bfs-queue)
                   (concat graphs))))]
    (->> graph
         bfs-queue
         (map first))))

(defn bfs-aj [graph start]
  (letfn [(more [head] (graph head)) ;; externalize "more" function
          (lazy-walk [& heads]
            (when heads
              (->> heads
                   (mapcat more)
                   (apply lazy-walk)
                   (lazy-cat heads))))] ;; NOTE we process heads first!
    (lazy-walk start)))

(comment
  tree
  (= (bfs tree)
     (bfs-aj tree-aj 1)))
