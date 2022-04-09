(ns coding-quizzes.matrix.matrix-rotate)

(def ^:private matrix1
  [[:a :b :c :d]
   [:e :f :g :h]
   [:i :j :k :l]
   [:m :n :o :p]])

(def ^:private matrix2
  [[:a :b]
   [:e :f]
   [:i :j]
   [:m :n]])

(def ^:private rotated1
  [[:m :i :e :a]
   [:n :j :f :b]
   [:o :k :g :c]
   [:p :l :h :d]])

(def ^:private rotated2
  [[:m :i :e :a]
   [:n :j :f :b]])

(defn- gen-matrix [I J]
  (mapv (fn [i]
          (mapv (fn [j] [i j])
                (range J))) (range I)))

(defn- mult [A B] ;;
  (let [I (count A) ;; rows
        J (count (first B)) ;; cols
        K (count B)] ;; A's cols and B's rows
    (assert (= (count (first A)) K)
            "A cols must be equal to B rows")
    (for [i (range I)]
      (for [j (range J)]
        (reduce (fn [s k]
                  (+ s (* (get-in A [i k])
                          (get-in B [k j]))))
                0 (range K))))))

(defn transpose-2d [M]
  (assert (and (vector? M) (vector? (first M)))
          "Must be in vector form")
  (let [n (count M)
        m (count (first M))]
    (mapv (fn [i]
            (mapv (fn [j] (get-in M [j i]))
                  (range n))) (range m))))

(defn reverse-rows [M]
  (vec (rseq M)))

(def rotate-90 (comp transpose-2d reverse-rows))

(comment
  ;; transpose logic
  (transpose-2d matrix1)
  (transpose-2d matrix2)

  ;; square matrix
  (= (rotate-90 matrix1) rotated1)
  ((comp transpose-2d reverse-rows) matrix1) ;; rotate 90 degreee clockwise
  ((comp reverse-rows transpose-2d) matrix1) ;; rotate 90 degress anticlockwise

;; rectangle matrix
  (= (rotate-90 matrix2) rotated2)
  ((comp transpose-2d reverse-rows) matrix2) ;; rotate 90 degreee clockwise
  ((comp reverse-rows transpose-2d) matrix2) ;; rotate 90 degress anticlockwise

  (gen-matrix 3 2)

  ;; identity function
  (mult [[1 0 0]
         [0 1 0]
         [0 0 1]]
        [[1 4]
         [3 9]
         [8 6]]))
