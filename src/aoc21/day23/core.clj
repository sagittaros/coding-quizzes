(ns aoc21.day23.core)

(def puzzle {:a [0 0]
             :b [1 0]
             :c [2 0]
             :d [3 0]
             :e [4 0]
             :f [5 0]
             :g [6 0]
             :h [7 0]
             :i [8 0]
             :j [9 0]
             :k [10 0]
             :l [2 1]
             :m [4 1]
             :n [6 1]
             :o [8 1]
             :p [2 2]
             :q [4 2]
             :r [6 2]
             :s [8 2]})

(defn- abs [n] (max n (- n)))

(defn distance [[x y] [x' y']]
  (+ (abs (- x x'))
     (abs (- y y'))))

(comment
  (distance (:a puzzle) (:s puzzle))
  (distance (:a puzzle) (:p puzzle))
  (distance (:a puzzle) (:n puzzle)))
