(ns clojure-bytes.functions)

;; a function with one argument
(defn square [x]
  (* x x))

;; a private function. a private function can be called only within the namespace
(defn- cube [x]
  (* x x x))

(defn get-cube [x]
  (cube x))

;; a function with two argument
(defn multiply [x y]
  (* x y))

;; a function with n argument
(defn multiply-n [& xs]
  (reduce * xs))

(defn x-and-many-y [x & y]
  (println x)
  (println y))

;; function with optional parameter
(defn string->integer [str & [base]]
  (Integer/parseInt str (or base 10)))

(defn string->integer-with-params [str {:keys [base] :or {base 10}}]
  (Integer/parseInt str (or base 10)))

;; a conditional function based on arguments
(defn messenger
  ([] (println "Welcome to clojure!"))
  ([msg] (println msg)))

(defn messenger-p
  ([] (messenger-p "Hello world!"))
  ([msg] (println msg)))

;; aynonymous functions
;; Equivalent to: (fn [x] (+ 6 x))
#(+ 6 %)
(#(+ 6 %) 7)
(#(* %1 %2) 5 6)

;; JAVA INTEROP
; (.instanceMember instance args*)
; (.instanceMember Classname args*)
; (.-instanceField instance)
; (Classname/staticMethod args*)
; Classname/staticField

;; instance method
(.length "hello")
(.toUpperCase "hello")

;; instance field
(.-x (java.awt.Point. 1 2))

;; static method
(Math/sqrt 25)

;; static field
(System/currentTimeMillis)

;; Instantiation
(def foo (java.util.HashMap.))
(-> foo (.put :a "c"))
(-> foo (.put :b "d"))