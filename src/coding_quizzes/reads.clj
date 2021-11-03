(ns coding-quizzes.reads
  (:require [clojure.walk :as walk]))

(comment
  ;; Interleave vs zipmap
  ;; https://clojuredocs.org/clojure.core/interleave
  ;; https://clojuredocs.org/clojure.core/zipmap
  (into [] (interleave [:fruit :color :temp :extra]
                       ["grape" "red" "hot"]
                       [100 200 300]))
  (apply assoc {}
         (interleave [:fruit :color :temp]
                     ["grape" "red" "hot"]))
  (zipmap [:fruit :color :temp :extra]
          ["grape" "red" "hot"]))

(comment
    ;; vector to indexed vector
  (map-indexed (comp first list) [:a :b :c])
  (range (count [:a :b :c])))

(comment
    ;; alternative way to unnest vector by 1 level
  (let [xs [[:a :b :c]
            [:e :f :g]]]
    (for [inner xs
          x inner]
      x)))

(comment
  ;; understand seq, seqable, lazy-seq
  ;; https://clojuredocs.org/clojure.core/seqable_q
  ;; https://clojuredocs.org/clojure.core/seq_q
  ;; https://clojuredocs.org/clojure.core/lazy-seq
  (seq [1 2 3])
  (seq {:a 1 :b 2})
  (seqable? [1 2 3])
  (seq? [1 2 3])
  (lazy-seq [1 2 3])
  (lazy-seq {:a 1 :b 2}))

(comment
  ;; it is easier to deal with seq than map
  (seq {:a {:b 2}
        :c {:d 4 :e 5}
        :f 100})
  (= (tree-seq map? keys
               {:a {:b 2}
                :c {:d 4 :e 5}
                :f 100})
     (tree-seq seqable? keys
               {:a {:b 2}
                :c {:d 4 :e 5}
                :f 100})))

(comment
  ;; rest vs next
  ;; https://stackoverflow.com/questions/4288476/clojure-rest-vs-next

  (next '(1)) ;; returns nil (realize it)
  (rest '(1)) ;; empty list (still lazy)

  ;; otherwise equivalent
  (next [1 2 3])
  (rest [1 2 3]))

(comment
  ;; https://clojuredocs.org/clojure.core/tree-seq
  ;; Take a nested structure and turns it into an iterable (with possible overlaps)
  (let [kv-tree {:a {:b 2}
                 :c {:d 4 :e 5}
                 :f 100}
        v-tree [:a [:b 2]
                :c [:d 4 :e 5]
                :f :g :h]
        ex :map4]
    (case ex
      :map1 (tree-seq map?
                      #(interleave (keys %) (vals %)) ;; flatten
                      kv-tree)
      :map2 (tree-seq map? keys kv-tree)
      :map3 (tree-seq map? vals kv-tree)
      :map4 (when (= (tree-seq map? seq kv-tree)
                     (tree-seq map? identity kv-tree))
              (tree-seq map? identity kv-tree))
      :vec1 (tree-seq vector? next v-tree)
      :vec2 (when (= (tree-seq vector? seq v-tree)
                     (tree-seq vector? identity v-tree))
              (tree-seq vector? seq v-tree))))

  (defn tree-seq-clone
    "Returns a lazy sequence of the nodes in a tree, via a depth-first walk.
   branch? must be a fn of one arg that returns true if passed a node
   that can have children (but may not).  children must be a fn of one
   arg that returns a sequence of the children. Will only be called on
   nodes for which branch? returns true. Root is the root node of the
  tree."
    [branch? children root]
    (let [walk (fn walk [node]
                 (lazy-seq
                  (cons node
                        (when (branch? node)
                          ;; unpack the node and walk recursively
                          (mapcat walk (children node))))))]
      (walk root)))

  ;; preserve parent
  (tree-seq-clone vector? seq [1 [2 [3 4 5]]])
  ;; discard parent
  (tree-seq-clone vector? next [1 [2 [3 4 5]]]))

(comment
  ;; use prewalk or postwalk to process item
  ;; prewalk goes from top to bottom,
  ;; postwalk goes from bottom to top
  (let [matrix [[1 2 3]
                [4 5 6]
                [7 8 9]]]
    (walk/postwalk #(if (number? %) (inc %) %) matrix)
    (walk/prewalk #(if (number? %) (inc %) %) matrix)))

;; ===========================================================
;; TODO Seq is powerfu!! learn them
;; https://clojure.org/reference/sequences#_the_seq_library
;; ===========================================================

;; ===========================================================
;; TODO Read clojure source!
;; https://github.com/clojure/clojure/blob/clojure-1.10.1/src/clj/clojure/core.clj#L4931
;; ===========================================================
