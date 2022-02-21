(ns coding-quizzes.tree-dfs)

;     1
;    / \
;   2   3
;      / \
;     4   5
;    /
;   66

;; ========================================================================
;; Struct method, less error prone but unable to support dynamic branch sizes
;; ========================================================================
(defrecord Tree [val left right])
(defn mt [val & [left right]]
  (->Tree val left right))

(def tree1 (mt 1
               (mt 2 nil 8) ;; test adding right node to 2
               (mt 3
                   (mt 4 (mt 66))
                   (mt 5))))

(defn dfs1 [{:keys [val left right]}]
  (concat [val]
          (condp apply [left]
            map? (dfs1 left)
            int? [left]
            nil? nil)
          (condp apply [right]
            map? (dfs1 right)
            int? [right]
            nil? nil)))

(comment
  tree1
  (dfs1 tree1))

;; ========================================================================
;; Use vector to represent tree
;; ========================================================================

;; tree pattern
'[root [left] [right]]
'[root
  [left
   [left of left]
   [right of left]]
  [right]]

(def tree2 [1 [2] [3 [4 [66]] [5]]])

(defn dfs2 [val & [left right]]
  (concat [val]
          (condp apply [left]
            vector? (apply dfs2 left)
            some? [left]
            nil? nil)
          (condp apply [right]
            vector? (apply dfs2 right)
            some? [right]
            nil? nil)))

(comment
  tree2
  (apply dfs2 tree2)
  (apply dfs2 [1 [2] [3] [4]])) ;; oops! only (1 2 3) is returned

;; ========================================================================
;; Now let's try multiple children
;; ========================================================================

;     1-------
;    / \      \
;   2   3     9
;      / \   / \
;     4   5 8  28
;    /
;   66

;; actually, if we remove the brackets, it is already in dfs form!
(def tree3 [1 [2] [3 [4 [66]] [5]] [9 [8] [28]]])

(defn dfs3 [val & children]
  (->> children
       (mapcat #(condp apply [%]
                  vector? (apply dfs3 %)
                  some? [%]
                  nil? nil))
       ((partial cons val))))

(comment
  tree3
  (apply dfs3 tree3)
  (apply dfs3 [1 [2] [3] [4 [8]] [5]]))

;; ========================================================================
;; The simplest 1 liner
;; ========================================================================

(defn dfs [[val & children]]
  (cons val (mapcat dfs children)))

(comment
  ;; this is already done! but at the cost of O(N log N)
  (flatten tree3)
  ;; using tree-seq
  ;; tree-seq returns a "focus" window, and we are only interested in the root of each window
  (map first (tree-seq next rest tree3))

  (dfs tree3))
