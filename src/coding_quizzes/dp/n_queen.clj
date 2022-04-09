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
  (let [count (atom 0)]
    (letfn
     [(mk-state []
        {:cols (vec (repeat n false))
         :diag1 (vec (repeat (inc (* 2 (dec n))) false))
         :diag2 (vec (repeat (inc (* 2 (dec n))) false))})
      (search [y {:keys [cols diag1 diag2] :as state}]
        (doseq [x (range n)]
          (let [d1-idx (+ x y)
                d2-idx (-> x (- y) (+ n) dec)]
            (cond
              (= y n) ;; terminate
              (swap! count inc)

              (or (cols x) (diag1 d1-idx) (diag2 d2-idx)) ;; clashed
              :clashed

              :else ;; continue search by moving down the row
              (search (inc y)
                      (-> state
                          (update :cols #(assoc % x true))
                          (update :diag1 #(assoc % d1-idx true))
                          (update :diag2 #(assoc % d2-idx true))))))))]
      (search 0 (mk-state))
      @count)))

(comment
  ;; (diag-ltr-debug 3)
  ;; (diag-rtl-debug 6)

  (println "------")
  (n-queen 4)

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
