(ns clojure-bytes.thread-macros)

;; (-> x & forms)

;; Threads the expr through the forms. Inserts x as the
;; second item in the first form

(def person
  {:name     "Mark Volkmann"
   :address  {:street "644 Glen Summit"
              :city   "St. Charles"
              :state  "Missouri"
              :zip    63304}
   :employer {:name    "Object Computing, Inc."
              :address {:street "12140 Woodcrest Dr."
                        :city   "Creve Coeur"
                        :state  "Missouri"
                        :zip    63141}}})

;; without using macros. writing as a function inside a function inside a function
(:city (:address (:employer person)))
;; "Creve Coeur"

;; the same scenario with a macro
(-> person :employer :address :city)
;"Creve Coeur"

;; add 2 to a number, square the response and subtract 1
(defn square [x]
  (* x x))

(defn add-n [x y]
  (+ x y))

;; the macro in action.
(defn try-it [x]
  (-> x
      (add-n 2)
      square
      (- 1)))
;;(try-it 8)
;; => 99

;; (->> x & forms)

;; Threads the expr through the forms. Inserts x as the
;; last item in the first form

;; An example of using the "thread-last" macro to get
;; the sum of the first 10 even squares.
(->> (range)
     (map #(* % %))
     (filter even?)
     (take 10)
     (reduce +))
;; => 1140

;; Comparison of -> and ->>
;; ->> passes as last argument
(->> "hello" (str "world "))
;; "world hello"

;; -> passes as the first argument
(-> "hello" (str " world"))
;;  "hello world"

