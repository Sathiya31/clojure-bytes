(ns clojure-bytes.set-select)

;; select

;; (select pred xset)

;; Returns a set of the elements for which pred is true

(clojure.set/select odd? #{1 2 3})
;;=> #{1 3}