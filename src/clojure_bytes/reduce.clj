(ns clojure-bytes.reduce)

;; reduce returns a single result from a list by applying a function on the list

;; (reduce f coll)(reduce f val coll)

;; f should be a function of 2 arguments.
;; If val is not supplied, returns the result of applying f to the first 2 items in coll,
;; then applying f to that result and the 3rd item, etc.
;; If val is supplied, returns the result of applying f to val and the first item in coll,
;; then applying f to that result and the 2nd item, etc

(reduce + [1 2 3 4 5 6 7 8 9 10])
;=> 55

;; this is how it works
(+ (+ (+ (+ (+ (+ (+ (+ (+ 1 2) 3) 4) 5) 6) 7) 8) 9) 10)
;=> 55

;; reduce with a initial value
(reduce + 6 [1 2 3])
;=> 12

;; A practical example of mapping over values
;; in a hash-map with the `upper-case` function:
(reduce
  (fn [acc [k v]]
    (assoc acc k (clojure.string/upper-case v)))
  {}
  {:a "aaaaaaa" :b "bbbbbbb"})

;; => {:a "AAAAAAA", :b "BBBBBBB"}

;;
(def users ["ben", "ben", "adam", "priya" "john", "john", "john"])

(reduce (fn [prev user]
          (assoc prev user (inc (prev user 0))))
        {} users)