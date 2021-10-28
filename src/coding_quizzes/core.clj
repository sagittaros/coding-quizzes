(ns coding-quizzes.core
  (:gen-class)
  (:require
   [coding-quizzes.largest-rect :refer [find-largest-rect]]))

(defn -main
  []
  (prn "find-largest-rect:" (find-largest-rect)))
