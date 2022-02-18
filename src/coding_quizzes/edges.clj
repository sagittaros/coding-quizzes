(ns coding-quizzes.edges)

(def tree [:root
           [:a [:b [:c [:g]]]]
           [:d [:e [:f [:g]]]]])

(def expected [[:root :a] [:root :d] [:a :b] [:b :c] [:c :g] [:d :e] [:e :f] [:f :g]])

(defn vec->edges [v-tree]
  (->> v-tree
       ;; preorder traversal
       (tree-seq vector? next) ;; turn map structure into an iterable (DFS)
       (mapcat (fn [[a & children]]
                 (map (fn [[b]] [a b]) children)))))

(comment
  (map (fn [[b]] b) [[1 2 3 4]]) ;; => (1)
  (tree-seq vector? next tree)
  (tree-seq vector? rest tree)
  (tree-seq vector? identity tree)
  (tree-seq vector? next [:root
                          [:a [:c [:d]]]
                          [:b]])

  (->> tree
       vec->edges
       (= expected)))
;; => nil
;; => nil
