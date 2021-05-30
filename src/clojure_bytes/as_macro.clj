(ns clojure-bytes.as-macro
  (:require [clojure.string :as str]))

;; (as-> expr name & forms)

;; Binds name to expr, evaluates the first form in the lexical context
;; of that binding, then binds name to that result, repeating for each
;; successive form, returning the result of the last form.

(as-> 0 n
      (inc n)                                               ; n is 0 here passed from first parameter to as->
      (inc n))                                              ; n is 1 here passed from result of previous inc expression
;;=> 2

;; split the james and append a string to the split values
(as-> "john, james, joe" s
      (str/split s #", ")
      (mapv #(str "hello " %) s))

;; split a string of values joined by delimiter
(as-> "james:23:abc-limited" s
      (str/split s #":")
      {:name    (first s)
       :age     (second s)
       :company (last s)})
;; => {:name "james", :age "23", :company "abc-limited"}