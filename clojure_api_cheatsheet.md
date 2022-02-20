# Clojure Cheatsheet

## Sequence manipulation

#### Conj

conjoin, most important API!

conjoining also works for `nil`

```clojure
;; for vector, it is like `appends`
(conj [1 2 3] 4) => [1 2 3 4]

;; for list, it is like `prepend`
(conj '(1 2 3) 4) => (4 1 2 3)

;; for map, it is like merge, right overriding left
(conj {1 2} {3 4})

;; for set, it is like add
(conj #{1 2} 3)

(conj nil 2) => (2)
```

#### Cons

I seldom use this

#### Pop vs Peek

```clojure
;; for vector, `pop` is like `rest` and `drop-last 1`, but it does not mutate into seq/list
(pop [1 2 3]) => [1 2]
(drop-last 1 [1 2 3]) => (1 2)

;; for list, `pop` takes first element of, inverse of `cons`
(pop '(1 2 3)) => (2 3)

;; note, popping the first element can also be done with `rest` or `next`
(rest '(1 2 3)) => (2 3)
(rest [1 2 3]) => (2 3)

;; or using subvec
(subvec [1 2 3] 1) => [2 3]
```

peek behavior depends on data

```clojure
;; for vector, it is the same as `last`, but faster?
(peek [1 2 3]) => 3

;; for list, it is the same as `first`
(peek '(1 2 3)) => 1
```

#### Comparisons among keep, filter, map and for.

`keep` is like `map`, but removes nil values from result
`keep` is like `filter`, but it does not filter `false` values

I think, avoid it in live coding session

```clojure
(keep #(if (odd? %) %) (range 10))
;;=> (1 3 5 7 9)

(map #(if (odd? %) %) (range 10))
;;=> (nil 1 nil 3 nil 5 nil 7 nil 9)

(for [ x (range 10) :when (odd? x)] x)
;;=> (1 3 5 7 9)

(filter odd? (range 10))
;;=> (1 3 5 7 9)
```

#### First and rest

first and rest is always used together
it is also possible to destructure like this:

```clojure 
(defn [[x & more]]
  (prn x more))

```

#### Filter vs Remove

```clojure
;; keeps only when fn returns truthy
(filter fn ...)

;; remove when fn returns falsey
(remove fn ...)
```

#### Take vs Drop

```clojure
;; take first n elements
(take n [...])
(take n '(...)) ;; works similarly


;; drop first n elements and return remaining/rest of elements
(drop n [...])
(drop n '(...)) ;; works similarly
```

#### Take-Last vs Drop-Last

```clojure
;; take last n elements
;; unlike `drop n`, `take-last n` always return n elements
(take-last n [...])
(take-last n '(...)) ;; works similarly

;; drop last n elements
;; unlike `take n`, `drop-last n` does not know how many elements are returned 
(drop-last n [...])
(drop-last n '(...)) ;; works similarly
```

#### Empty? vs (> (count ...) 0)

use `empty?` instead of `count` for all sequential data (vector, list, seq/nil, map, set)

#### Subvec

take subset of vector from start(incl) to end(excl)

#### Vec vs Vector

`vector` takes in &args and turn them into vector
`vec` takes sequentials (list, map, set, seq/nil) and turn into vector

#### Rest vs Next

`next` and `rest` does the same thing. But `rest` will continue to operate in seq, whereas `next` will ends up with `nil`

```clojure
(rest [1 2 3]) => '(2 3)
(rest '(1 2 3)) => '(2 3)
```

#### Repeat vs Repeatedly

Completely different things

```clojure
(repeat 3 5) => '(5 5 5)

(repeatedly 3 #(+ 1 1)) => '(2 2 2)
```


#### Juxt

apply a series of fn to a single object and return as vector

```clojure
((juxt identity name) :keyword)
;;=> [:keyword "keyword"]
```

#### Sorting

https://andersmurphy.com/2019/03/09/clojure-sorting.html

- sort is the simplest low to high sort (takes optional `compare` as params)
- sort-by takes in a function that derive sortable value (also takes optional `compare` as params)
- compare is a way to implement Comparable, by specifying (compare a b) or (compare b a)


#### Mapcat

like `map` but concats/flatten-1. It is sometimes useful to wrap with `(map vec)` so it becomes sortable, see [permutation](./src/coding_quizzes/backtrack/permutation.clj)

## Set

#### Index

https://clojuredocs.org/clojure.set/index
