(ns clojure-bytes.some-macros)

;; (some-> expr & forms)

;; When expr is not nil, threads it into the first form (via ->),
;; and when that result is not nil, through the next etc

;; when thread macros faces nil
(-> {:a 1} :b inc)
;; Execution error (NullPointerException) at clojure-bytes.core/eval1583 (form-init5046890141390764530.clj:1).

;; when some-> faces nil
(some-> {:a 1} :b inc)
;; nil

;; (some->> expr & forms)

;; When expr is not nil, threads it into the first form (via ->>),
;; and when that result is not nil, through the next etc

;; when some->> macro faces nil
(some->> {:y 3 :x 5}
         (:z)
         (- 2))


;; a normal thread macro
(->> {:y 3 :x 5}
     (:z)
     (- 2))
;; Execution error (NullPointerException) at clojure-bytes.core/eval1597 (form-init5046890141390764530.clj:3).