(ns coding-quizzes.strings.longest-substr
  (:require [clojure.string :as str]))

(defn substrings [s]
  (letfn
  ;; ss = all substrings
  ;; wm = window
  ;; i = start index of the curr window
  ;; c = current char
  ;; j = current index
   [(scan [{:keys [ss wm i]} [j c]]
      (cond
        ;; terminating condition
        (= (inc j) (count s))
        (if (some? (wm c))
          (conj ss (subs s i j) (str c))
          (conj ss (subs s i (inc j))))

        (some? (wm c))
        {:wm {c j}
         :ss (conj ss (subs s i j))
         :i  j}

        :else {:wm (assoc wm c j)
               :ss ss
               :i  i}))]
    (->> s
         (zipmap (range))
         (reduce scan {:wm {} :ss [] :i 0}))))

(defn longest-substr [s]
  (letfn
   [(longest [{:keys [longest wm i]} [j c]]
      (cond
        ;; terminating condition
        (= (inc j) (count s))
        (let [incl-last-letter (if (some? (wm c)) 0 -1)]
          (max longest (- j i incl-last-letter)))

        ;; start new window and compare for longest substr
        (some? (wm c))
        {:wm {c j} :i j
         :longest (max longest (- j i))}

        ;; expand window
        :else {:wm (assoc wm c j) :i i
               :longest longest}))]
    (->> s
         (zipmap (range))
         (reduce longest {:wm {} :longest 0 :i 0}))))

(comment
  (str/includes? "abc" "z")
  (subs "cza" 1 2)
  (count "cza")
  (substrings "aaabcdag")
  (longest-substr "aaabcdag")
  (substrings "abcabcd")
  (longest-substr "abcabcd")
  (substrings "bbbbb")
  (longest-substr "bbbbb")
  (substrings "pwwkew")
  (longest-substr "pwwkew"))
