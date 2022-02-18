(ns coding-quizzes.hanoi)

;; def toh(n , source, dest, aux):
;;   if n == 1:
;;     print("Move disk 1 from tower",source,"to tower",dest)
;;     return
;;   toh(n-1, source, aux, dest)
;;   print("Move disk",n,"from rod",source,"to rod",dest)
;;   toh(n-1, aux, dest, source)
;; n = 6
;; toh(n, 'A', 'C', 'B')

(defn tower-of-hanoi [n src dest aux]
  (if (= n 1)
    (println "Move disk 1 from" src "to" dest)
    (do
      (tower-of-hanoi (- n 1) src aux dest)
      (println "Move disk" n "from" src "to" dest)
      (tower-of-hanoi (- n 1) aux dest src))))

(comment
  (println "NEW")
  (tower-of-hanoi 3 :a :c :b))
