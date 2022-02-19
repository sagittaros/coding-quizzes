# Clojure Cheatsheet

## Sequence manipulation

#### Conj

conjoin, most important API!

```clojure
;; for vector, it is like `appends`
(conj [1 2 3] 4) => [1 2 3 4]

;; for list, it is like `prepend`
(conj '(1 2 3) 4) => (4 1 2 3)

;; for map, it is like merge, right overriding left
(conj {1 2} {3 4})

;; for set, it is like add
(conj #{1 2} 3)
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

