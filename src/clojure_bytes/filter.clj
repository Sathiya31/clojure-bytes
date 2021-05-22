(ns clojure-bytes.filter)

;; (filter pred coll)
;; Filter takes a predicate and evaluates across the collection and returns a collection that returns true

(filter even? [1 2 3 4 5 6 7 8 9 10])
;=> (2 4 6 8 10)

;; filterv does the same but returns a vector
(filterv even? [1 2 3 4 5 6 7 8 9 10])
;;=> [2 4 6 8 10]

;; filter similar to maps can take in custom functions
(filter (fn [x]
          (= (count x) 1))
        ["a" "aa" "b" "n" "f" "lisp" "clojure" "q" ""])
;;=> ("a" "b" "n" "f" "q")

;; filter with anonymous function
(filter #(= (count %) 1)
        ["a" "aa" "b" "n" "f" "lisp" "clojure" "q" ""])
;;=> ("a" "b" "n" "f" "q")

;;practical example using an anonymous function
;;which return a boolean value
(def entries [{:month 1 :val 12 :s1 true :s2 false}
              {:month 2 :val 3 :s1 false :s2 true}
              {:month 3 :val 32 :s1 true :s2 false}
              {:month 4 :val 18 :s1 true :s2 false}])

(filter #(:s2 %) entries)
;;=> ({:month 2, :val 3, :s1 false, :s2 true})

;; keywords are also functions
(filter :s2 entries)
;;=> ({:month 2, :val 3, :s1 false, :s2 true})

;; filter with multiple conditions
(filter #(and (:s2 %) (> (:val %) 30)) entries)
;;=> ()
(filter #(and (:s2 %) (< (:val %) 30)) entries)
;;=> ({:month 2, :val 3, :s1 false, :s2 true})