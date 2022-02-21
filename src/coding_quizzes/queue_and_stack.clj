(ns coding-quizzes.queue-and-stack)

;; ==============================
;; use vector as queue

(defn enqueue [q & items]
  (apply conj q items))

(defn dequeue [q & [n]]
  (list (take (or n 1) q)
        (drop (or n 1) q)))

(comment
  (enqueue [1 2 3] 4 5)
  ;; simply conj to enqueue
  (conj [1 2 3] 4 5)
  (dequeue [1 2 3 4 5])
  ;; or simply destructure
  (let [[first & rest] [1 2 3 4 5]]
    [first rest]))

;; ==============================
;; Stack
;; again, use vector

(comment
  ;; push to stack
  (conj [1 2 3] 4 5)
  ;; pop stack
  (pop [1 2 3])
  ;; peek stack
  (peek [1 2 3]))
