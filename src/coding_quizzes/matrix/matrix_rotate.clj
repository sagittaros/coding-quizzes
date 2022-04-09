(ns coding-quizzes.matrix.matrix-rotate)

(def ^:private matrix
  [[:a :b :c :d]
   [:e :f :g :h]
   [:i :j :k :l]
   [:m :n :o :p]])

(def ^:private rotated
  [[:m :i :e :a]
   [:n :j :f :b]
   [:o :k :g :c]
   [:p :l :h :d]])

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
  (assert (= (count M) (count (first M)))
          "Must be n*n square matrix")
  (let [n (range (count M))
        M' (atom M)]
    (doseq [i n]
      (doseq [j n]
        (swap! M' assoc-in [i j] (get-in M [j i]))))
    @M'))

(defn reverse-rows [M]
  (vec (rseq M)))

(def rotate-90 (comp transpose-2d reverse-rows))

(comment
  (= (rotate-90 matrix) rotated)

  ;; look at various composition
  ((comp transpose-2d reverse-rows) matrix) ;; rotate 90 degreee clockwise
  ((comp reverse-rows transpose-2d) matrix) ;; rotate 90 degress anticlockwise
  (transpose-2d matrix)

  (gen-matrix 3 2)

  ;; identity function
  (mult [[1 0 0]
         [0 1 0]
         [0 0 1]]
        [[1 4]
         [3 9]
         [8 6]]))
