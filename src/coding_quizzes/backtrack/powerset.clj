(ns coding-quizzes.backtrack.powerset)

(defn powerset [choices]
  (letfn
   [(dive [parent i] ;; walked should be vector
      (let [with (conj parent (nth choices i))
            without parent
            i' (inc i)]
        (if (= i (->> choices count dec))
          [with without]
          ;; or `apply concat`, `mapcat identity`
          (mapcat seq ;; collect(unpack) recursive calls
                  [(dive with i')
                   (dive without i')]))))]
    (dive [] 0)))

(comment
  (powerset [1 2 3]))
