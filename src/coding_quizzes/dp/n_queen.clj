(ns coding-quizzes.dp.n-queen)

;; (defn- diag-ltr-idx [x y]
;;   (+ x y))

;; (defn- diag-rtl-idx [x y n]
;;   (-> x (- y) (+ n) dec))

;; (defn- diag-ltr-debug [n]
;;   (for [i (range n)]
;;     (for [j (range n)]
;;       (diag-ltr-idx i j))))

;; (defn- diag-rtl-debug [n]
;;   (for [i (range n)]
;;     (for [j (range n)]
;;       (diag-rtl-idx i j n))))

(defn n-queen [n]
  (let [count (atom 0)
        valid-solutions (atom [])]
    (letfn
     [(mk-state []
        {:board (->> 0 (repeat n) vec (repeat n) vec)
         :cols (vec (repeat n false))
         :diag1 (vec (repeat (inc (* 2 (dec n))) false))
         :diag2 (vec (repeat (inc (* 2 (dec n))) false))})
      (search [y {:keys [board cols diag1 diag2] :as state}]
        (run! ;; run! is similar to reduce
         (fn [x] (let [d1-idx (+ x y)
                       d2-idx (-> x (- y) (+ n) dec)]
                   (cond
                     (= y n) ;; beyond bound, solved!
                     (do
                       (swap! count inc)
                       (swap! valid-solutions conj board)
                       (reduced :done)) ;; reduced prematuraly terminate iteration, like `break`

                     (or (cols x) (diag1 d1-idx) (diag2 d2-idx)) ;; clashed
                     :clashed

                     :else ;; continue search by moving down the row
                     (search (inc y)
                             (-> state
                                 (update :board #(assoc-in % [x y] 1))
                                 (update :cols #(assoc % x true))
                                 (update :diag1 #(assoc % d1-idx true))
                                 (update :diag2 #(assoc % d2-idx true)))))))
         (range n)))]
      (search 0 (mk-state))
      [@count @valid-solutions])))

(comment
  ;; (diag-ltr-debug 3)
  ;; (diag-rtl-debug 6)

  (println "------")
  (first (n-queen 5))

;; column
  [[0 1 2 3]
   [0 1 2 3]
   [0 1 2 3]
   [0 1 2 3]]

  ;; NOTE observation, there is always 2(n-1) diagonals
  ;; diagonal top-left -> bottom right
  [[0 1 2 3]
   [1 2 3 4]
   [2 3 4 5]
   [3 4 5 6]]

  ;; diagonal top-right -> bottom left
  [[3 2 1 0]
   [4 3 2 1]
   [5 4 3 2]
   [6 5 4 3]])
