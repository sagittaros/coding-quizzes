# coding-quizzes

My (messy) clojure code is [here](src/coding_quizzes)!

# Reading materials

- https://www.youtube.com/c/NeetCode/videos
- https://labuladong.gitbook.io/algo-en/

# Coding questions

- codeforces
- https://leetcode.com/list/xi4ci4ig/
- https://www.adamconrad.dev/blog/changing-jobs-during-the-great-resignation/

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
- `mapcat or lazy-cat` is useful for backtracking, to collect the result of decision/choice branches
- `corecursion` is useful for series induction
- `reduce` is useful to convergent computation (many to 1)
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

For example, [powerset](src/coding_quizzes/powerset.clj) has the first loop based on `2^n`, and the inner loop `n`, so it combined into `n*2^n`

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

# Tree algorithms

https://towardsdatascience.com/4-types-of-tree-traversal-algorithms-d56328450846#:~:text=Preorder%20Traversal%20is%20another%20variant,to%20the%20left%20sub-tree.

# Heap 

Heap is a less-ordered version of binary search tree.

It has a few notable properties:
1. There is either min-heap (where root is minimum) or max-heap (where root is maximum)
2. The tree must be complete:
   - all levels except last must be filled
   - last level must be filled from left to right
3. it uses trickle up for insertion (swapping lesser with bigger, or bigger with lesser)
4. it uses swap and trickle down for deletion (swap with last node)
5. the last node is important, and it is always at the right-most bottom level
6. 

Commonly used for priority queue.
It is a prerequisites for Dijkstra's Shortest Path and Prim's Minimum Spanning Tree

- https://www.geeksforgeeks.org/binary-heap/?ref=lbp
- [Clojure implementation of binary heap](https://gist.github.com/justinhj/7074000)
- [Fibonacci heap](https://maryrosecook.com/blog/post/the-fibonacci-heap-ruins-my-life)

# Binary Search Tree

TODO

Compare with heap


# Graph search problem

https://stackoverflow.com/questions/47783359/how-to-implement-a-recursive-dfs-in-clojure-without-using-a-vector-stack

[Dijkstra implementation in clojure](https://github.com/maryrosecook/dijkstra)

- DFS (use tree-seq if possible)
- BFS (refer "CoRecursion in Clojure")
- Trie
- Djisktra problem
- Bellman Ford
- Minimum Spanning Tree

# Backtracking

My weakest area!!

Backtracking usually means exhausting the decision tree, like in a permutation problem.
The time complexity is usually factorial time: O(n!)

- https://labuladong.gitbook.io/algo-en/iii.-algorithmic-thinking/detailsaboutbacktracking
- https://www.baeldung.com/cs/backtracking-algorithms
- https://www.baeldung.com/cs/backtracking-vs-dfs
- https://youtu.be/Ph95IHmRp5M (N-Queens Problem)

``` python
# pseudocode of backtracking framework
result = []
def backtrack(Path, Seletion List  ):
    if meet the End Conditon:
        result.add(Path)
        return

    for seletion in Seletion List:
        select
        backtrack(Path, Seletion List)
        deselect
```

# Game theory and min-max algorithm

https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/

# Pattern searching

Also my weakest area!!

- https://www.geeksforgeeks.org/naive-algorithm-for-pattern-searching/?ref=lbp
- https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/?ref=lbp

# Dynamic Programming

- https://www.youtube.com/watch?v=H9bfqozjoqs
- https://www.geeksforgeeks.org/longest-palindrome-substring-set-1
- https://www.geeksforgeeks.org/sieve-of-eratosthenes/?ref=leftbar-rightbar
- https://www.geeksforgeeks.org/edit-distance-dp-5/

# Greedy algorithms


# Simulated Arithmetic

- https://www.geeksforgeeks.org/square-root-of-a-perfect-square/?ref=lbp
- https://adventofcode.com/2021/day/24

# More things!

- [CoRecursion](http://squirrel.pl/blog/2010/07/26/corecursion-in-clojure/)
- [Old problems](https://gist.github.com/sagittaros/d939836282043500fa907637c611cff9)
- [Clique problem](https://en.wikipedia.org/wiki/Clique_problem)
- [Basic data structure/problems](https://u.osu.edu/cstutorials/2016/11/21/7-algorithms-and-data-structures-every-programmer-must-know/)
- [Mazes](https://markbastian.github.io/posts-output/2015-04-11-mazes/)
- [Drop water problem](https://labuladong.gitbook.io/algo-en/iv.-high-frequency-interview-problem/trapping_rain_water)
- [Project Euler](https://projecteuler.net)

# Tricks?

- whatever is unknown/unsure, turn it into function first, and figure it out later
- try to visualize the recursion before starting
- do a lot of practice questions

# High probability questions

## Print all paths from source to dest

Time Complexity: O(V^V). 
The time complexity is polynomial. From each vertex there are v vertices that can be visited from current vertex.
Auxiliary space: O(V^V). 
To store the paths V^V space is needed.

- https://stackoverflow.com/questions/49209058/what-is-the-time-complexity-of-finding-all-possible-ways-from-one-to-another
- https://www.geeksforgeeks.org/find-paths-given-source-destination/
- https://adventofcode.com/2021/day/12
