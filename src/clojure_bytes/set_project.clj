(ns clojure-bytes.set-project)

;; (project xrel ks)

;; Returns a rel of the elements of xrel with only the keys in ks

(def cows #{{:name "betsy" :id 33 :place "Chennai"} {:name "panda" :id 34 :place "Bangalore"}})

(set/project cows [:name])
;; => #{{:name "panda"} {:name "betsy"}}

(set/project cows [:name :place])
;;=> #{{:name "panda", :place "Bangalore"} {:name "betsy", :place "Chennai"}}