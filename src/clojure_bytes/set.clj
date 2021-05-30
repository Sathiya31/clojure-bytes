(ns clojure-bytes.set)

;; (set coll)

;; Returns a set of the distinct elements of coll.
(set '(1 1 2 3 2 4 5 5))
;;=> #{1 4 3 2 5}

(->> ["james" "john" "joe" "john" "joe"]
    (into #{}))
;;=> #{"james" "john" "joe"}


;; (sorted-set & keys)
;; Returns a new sorted set with supplied keys.

;; sorted-set cannot take collection as argument. it takes only the values
(sorted-set 1 3 5 2 8)

;; practical use of sorted-set
(apply sorted-set '(1 1 2 3 2 4 5 5))
;;=> #{1 2 3 4 5}