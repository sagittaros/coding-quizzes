(ns aoc21.day24.core
  (:require
   [clojure.edn :as edn]
   [clojure.string :as str]))

(def opcodes #{"add" "mul" "div" "mod" "eql" "inp" "x" "y" "z" "w"})

(def parse
  (partial map #(if (opcodes %) (keyword %)
                    (edn/read-string %))))

(def instructions (as-> "src/aoc21/day24/input.txt" ?
                    (slurp ?)
                    (str/split ? #"\n")
                    (map #(str/split % #"\s") ?)
                    (map parse ?)))

(defn alu [input]
  {:memory {:x 0 :y 0 :z 0 :w 0}
   :input input})

(def ops {:mul *
          :add +
          :div (fn [a b] (int (/ a b)))
          :mod mod
          :eql (fn [a b] (if (= a b) 1 0))})

(comment
  instructions)
