(ns clojure-bytes.map)

;; map applies a function to each element in the collection and returns a lazy sequence
(map inc [1 2 3 4 5])
;; => (2 3 4 5 6)

;; mapv applies the functions and returns a vector
(mapv inc [1 2 3 4 5])
;; => [2 3 4 5 6]

;; map can be used with multiple collections. Collections will be consumed
;; and passed to the mapping function in parallel:
(map + [1 2 3] [4 5 6])
;;=> (5 7 9)

;; the function is applied till one of the collection is exhausted. the remaining are ignored
(map + [1 2 3] [4])
;;=> (5)

;; custom functions can be return to operate on a collection
(map (fn [x] (* x x)) [1 2 3 4])
;;=> (1 4 9 16)

;; anonymous functions can be used in function. the parameters are accessed with %
(map #(str "Hello " % "!" ) ["Ford" "Arthur" "Tricia"])
;;("Hello Ford!" "Hello Arthur!" "Hello Tricia!")
(map #(* % %) [1 2 3 4])
;;(1 4 9 16)

;; function with parameter can be passed
(defn square [x]
  (* x x))

(map #(square %) [1 2 3 4])
;;(1 4 9 16)

;; if only one argument needs to be passed the function name alone will be sufficient
(map square [1 2 3 4])
;;(1 4 9 16)

;; passing map as a collection gives map entries in each iteration
(map (fn [[key value]] [key (* 2 value)])
     {:a 1 :b 2 :c 3})
;;([:a 2] [:b 4] [:c 6])

;;map taking a collection of functions as an argument
(def sum #(reduce + %))
(def average #(/ (sum %) (count %)))

;;apply a function to a collection
(defn results [coll]
  (map #(% coll) [sum average count]))

(results [10 20 30 40 50])
;;[150 30 5]

;; map on a collection of objects
(def my-coll [{:m 1, :val 12}
              {:m 2, :val 3}
              {:m 3, :val 32}])

(map #(:val %) my-coll)
;;(12 3 32)

;; keywords are functions in clojure. and for function with one arguments they can be called directly
(map :val my-coll)
;;(12 3 32)

;; mapcat is combination of map and concat. it maps the values and concats the results
(map reverse [[3 2 1 0] [6 5 4] [9 8 7]])
;; => ((0 1 2 3) (4 5 6) (7 8 9))
(mapcat reverse [[3 2 1 0] [6 5 4] [9 8 7]])
;; => (0 1 2 3 4 5 6 7 8 9)
