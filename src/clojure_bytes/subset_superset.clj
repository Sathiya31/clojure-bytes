(ns clojure-bytes.subset-superset)

;; subset?

;; (subset? set1 set2)

;; Is set1 a subset of set2?

(clojure.set/subset? #{2 3} #{1 2 3 4})
;; => true

(clojure.set/subset? #{2 5} #{1 2 3 4})
;; => false

;; Switching to considering cases where the first argument is not a set, this
;; appears to do what one would hope:
(clojure.set/subset? [1 2 3 4] #{1 2 3 4 5})
;;true

;; ... but this does not (at least up until Clojure 1.10 this behavior is
;; because of how subset? is implemented, first comparing the count of the
;; two collections to make it possible to quickly return false when the first
;; collection is larger):
(clojure.set/subset? [1 2 3 4 1 2 3 4] #{1 2 3 4 5})
;;false


;; Superset

;; (superset? set1 set2)

;; Is set1 a superset of set2?

(clojure.set/superset? #{1} #{1})
;;=> true

(clojure.set/superset? #{1 2} #{1})
;;=> true

(clojure.set/superset? #{1} #{1 2})
;;=> false