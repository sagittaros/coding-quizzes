# coding-quizzes

# Recursion 
## Understand tower of hanoi
https://www.youtube.com/watch?v=rf6uf3jNjbo

time complexity of Tower of Hanoi is 2^n

## Understand recursion thinking patterns
https://www.youtube.com/watch?v=ngCos392W4w

## Understand tail recursion, trampoline, loop/recur
https://clojuredocs.org/clojure.core/recur

# Read basic sequence manipulation

- use (zipmap (range) items) to create indexed items
- [reads.clj](./src/coding_quizzes/reads.clj)
- [utils.clj](./src/coding_quizzes/utils.clj)
- `loop/recur` is useful for divergent computation (1 to many)
- `corecursion` is useful for series induction
- `for` is useful to convergent computation (many to 1)
- use `for` when nested loop is expected


# Understand complexity

Read this article for intuition [Intuition between permutation and combination](https://buildingvts.com/intuition-behind-permutations-and-combinations-db6ffa5272be)

Basic understanding of NP-completeness and complexity:
[Factorial time](https://jarednielsen.com/big-o-factorial-time-complexity/)

## Notations
- average case = theta
- worst case = Big O
- best case = Omega

## Understanding loops

Complexity of loop or nested loop = total loop count * complexity of iteration

https://www.enjoyalgorithms.com/blog/time-complexity-analysis-of-loop-in-programming

## Understanding series

https://www.toppr.com/guides/quantitative-aptitude/number-series/geometric-series/

#### Geometric series

Tower of hanoi falls under this

(n/(n-1)) = r, where r = ratio

## Common ones

#### log n (log time, *halving*)

When a problem is divide/conquered in the form of halving, the computation time became log time

#### n log n (aka log linear time)

Most sorting algorithms fall under this category.
Instead of n^2 (for each item, check the other items), it becomes n*(log n). When using tree/halving, the growth rate is always log n

#### 2^n (exponential time, *doubling*)
`2^n` implies a doubling nature. 2^1 = 2, 2^2 = 4, 2^3 = 8
`2^n` is also when recursive calls happen, where `n` is the `depth` or `height` of the tree.
Fibonacci sequence falls under this example

``` clojure
(+ (fib n) 
    (fib (dec n)))
```

There are variants of this class, "Cartesian product of arrays".
Given n = length, x = numOfArrays, the complexity is n^x

#### n! (factorial time)

This is the most expensive algorithm. Time-traveling-salesman and combinatorial problems fall under this. Because there are (factorial) amount of checks to perform.

They are also known as NP-complete problems

Use heuristics to perform this:
- simulated annealing
- ant colony optimization
- or use something like minizinc optimization library

# Familiar with bitwise operations

https://cek.io/blog/2017/08/17/clojure-introduction-binary-bitwise/

Refer [powerset.clj](src/coding_quizzes/powerset.clj)

- bit-and
- bit-or
- (bit-shift-right n x) (halves n, by removing x least significant bit)
- (bit-shift-left n x) (doubles n for x times)

# Use lispy stuff

use syntax quoting, unquote(~), and unquote-splicing(~@) to simplify code
https://clojure.org/guides/weird_characters#unqote

# Graph search problem

- DFS
- BFS
- Trie
- Djisktra problem
- Bellman Ford
- Minimum Spanning Tree

# Backtracking

- https://www.baeldung.com/cs/backtracking-algorithms
- https://www.baeldung.com/cs/backtracking-vs-dfs

# Game theory and min-max algorithm

https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/

# Pattern searching

My weakest area!!
https://www.geeksforgeeks.org/naive-algorithm-for-pattern-searching/?ref=lbp
https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/?ref=lbp

# Simulated Arithmetic

- https://www.geeksforgeeks.org/square-root-of-a-perfect-square/?ref=lbp
- https://adventofcode.com/2021/day/24

# More things!

- [CoRecursion](http://squirrel.pl/blog/2010/07/26/corecursion-in-clojure/)
- [Old problems](https://gist.github.com/sagittaros/d939836282043500fa907637c611cff9)
- [Clique problem](https://en.wikipedia.org/wiki/Clique_problem)
- [Basic data structure/problems](https://u.osu.edu/cstutorials/2016/11/21/7-algorithms-and-data-structures-every-programmer-must-know/)
- [Mazes](https://markbastian.github.io/posts-output/2015-04-11-mazes/)
