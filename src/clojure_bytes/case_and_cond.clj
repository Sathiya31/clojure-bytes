(ns clojure-bytes.case-and-cond)

;; (case e & clauses)

;; Takes an expression, and a set of clauses.
;; Each clause can take the form of either:
;; test-constant result-expr
;; (test-constant1 ... test-constantN)  result-expr

;; print size of the hello
(let [mystr "hello"]
  (case mystr
    "" 0
    "hello" (count mystr)))
;;=> 5

;; print size of the hello
(let [mystr "ab"]
  (case mystr
    "" (str " i am blank")
    "hello" (str "i am hello")
    ("ab" "cd") (str "i am ab or cd")))
;;=> "i am ab or cd"

;; if there are no matches case will give an execption
(let [mystr "abc"]
  (case mystr
    "" (str " i am blank")
    "hello" (str "i am hello")
    ("ab" "cd") (str "i am ab or cd")))
;; Execution error (IllegalArgumentException) at clojure-bytes.core/eval1634 (form-init5046890141390764530.clj:2).
;; No matching clause: abc

(let [mystr "abc"]
  (case mystr
    "" (str " i am blank")
    "hello" (str "i am hello")
    ("ab" "cd") (str "i am ab or cd")
    "i am none of the above"))
;; => "i am none of the above"


;;(cond & clauses)

;; Takes a set of test/expr pairs. It evaluates each test one at a
;; time.  If a test returns logical true, cond evaluates and returns
;; the value of the corresponding expr and doesn't evaluate any of the
;; other tests or exprs. (cond) returns nil.

(defn pos-neg-or-zero
  "Determines whether or not n is positive, negative, or zero"
  [n]
  (cond
    (< n 0) "negative"
    (> n 0) "positive"
    :else "zero"))

(pos-neg-or-zero 5)
;; "positive"
(pos-neg-or-zero -1)
;; "negative"
(pos-neg-or-zero 0)
;; "zero"

;; it doesnt evaluate the other conditions once one conditions returned true

(let [grade 85]
  (cond
    (>= grade 90) "A"
    (>= grade 80) "B"
    (>= grade 70) "C"
    (>= grade 60) "D"
    :else "F"))
;; => "B"

;; it doenst throw exception if else is not provided

(let [grade 55]
  (cond
    (>= grade 90) "A"
    (>= grade 80) "B"
    (>= grade 70) "C"
    (>= grade 60) "D"))
;; => nil


;; condp
;; (condp pred expr & clauses)

;; Takes a binary predicate, an expression, and a set of clauses.
;; Each clause can take the form of either:
;; test-expr result-expr
;;  test-expr :>> result-fn

(condp apply [:foo]
  string? "it's a string"
  keyword? "it's a keyword"
  symbol? "it's a symbol"
  fn? "it's a function"
  "something else!")
;;=> "it's a keyword"

;; this is what happens in conditions
;; (apply fn? [:foo])
;; => false
;; (apply keyword? [:foo])
;; => true

;; (some #{4 5 9} [1 2 3 4])
;;  is the first matching clause,
;;  the match value is 4 which is decremented
(condp some [1 2 3 4]
  #{0 6 7} :>> inc
  #{4 5 9} :>> dec
  #{1 2 3} :>> #(+ % 3))
;;=> 3

;; the third condition matches
(condp some [3 2 1]
  #{0 6 7} :>> inc
  #{4 5 9} :>> dec
  #{1 2 3} :>> #(+ % 3))
;; => 6