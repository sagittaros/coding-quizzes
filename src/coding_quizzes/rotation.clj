(ns coding-quizzes.rotation)

(defn start-at [s start & [len]]
  (let [slen (count s)
        len (or len slen)
        start' (mod start slen)
        head (subs s start' (min slen (+ start' len)))
        hlen (count head)
        more (- len hlen)]
    (prn head start len more)
    (if (< more 1) head
        (str head (start-at s (+ start hlen) more)))))

(defn is-rotation? [a b]
  (let [reverse? (> (count a) (count b))
        [a b] (if reverse? [b a] [a b])]
    (loop [i 0]
      (cond
        (= (start-at a i (count b)) b) true
        (= i (count b)) false
        :else (recur (inc i))))))

(comment
  (is-rotation? "bcabc" "abc"))
