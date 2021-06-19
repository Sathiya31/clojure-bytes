(ns clojure-bytes.set-functions
  (:require [clojure.set :as set :refer :all]))

;; Union

;; (union)(union s1)(union s1 s2)(union s1 s2 & sets)

;; Return a set that is the union of the input sets

(union #{1 2} #{2 3})
;; #{1 2 3}

(union #{1 2} #{2 3} #{3 4})
;; #{1 2 3 4}

;; union can be used to combine maps too. if any of the key or value varies its considered a different object
;; the map with same key and values are ignored
(union #{{:a "b"} {:c "d"}} #{{:a "c"} {:c "d"}})
;;=> #{{:a "c"} {:c "d"} {:a "b"}}


;; Intersection

;; (intersection s1)(intersection s1 s2)(intersection s1 s2 & sets)

;; Return a set that is the intersection of the input sets

(intersection #{1 2} #{2 3})
;;=> #{2}

(intersection #{1 2} #{2 3} #{3 4})
;;=> #{}

(intersection #{1 2} #{2 3} #{3 4 2})
;;=> #{2}

;; intersection works on maps too
(intersection #{{:a "b"} {:c "d"}} #{{:a "c"} {:c "d"}})
;;=> #{{:c "d"}}


;; difference

;; (difference s1)(difference s1 s2)(difference s1 s2 & sets)

;; Return a set that is the first set without elements of the remaining sets

(difference #{1 2} #{2 3})
;; #{1}

(difference #{1 2 3} #{1} #{1 4} #{3})
;; #{2}

;; Difference with maps
(difference #{{:a "b"} {:c "d"}} #{{:a "c"} {:c "d"}})
;;=> #{{:a "b"}}