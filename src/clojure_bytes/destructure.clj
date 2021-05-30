(ns clojure-bytes.destructure)

(def my-line [[5 10] [10 20]])

;; destructuring the two parameters and further destructuring each point
(let [p1 (first my-line)
      p2 (second my-line)
      x1 (first p1)
      y1 (second p1)
      x2 (first p2)
      y2 (second p2)]
  (println "Line from (" x1 "," y1 ") to (" x2 ", " y2 ")"))

(let [[p1 p2] my-line
      [x1 y1] p1
      [x2 y2] p2]
  (println "Line from (" x1 "," y1 ") to (" x2 ", " y2 ")"))

;; destructuring vector values
(def my-vector [1 2 3])

(let [[x y z] my-vector]
  (println x y z))

(let [[x y] my-vector]
  (println x y))

(let [[x & remaining] my-vector]
  (println x remaining))

(let [[x & remaining :as all] my-vector]
  (println x remaining)
  (println all))

;; Associative destructuring

(def client {:name "Super Co."
             :location "Philadelphia"
             :description "The worldwide leader in plastic tableware."})

(let [name (:name client)
      location (:location client)
      description (:description client)]
  (println name location "-" description))

;; extract using keys
(let [{:keys [name location description]} client]
  (println name location "-" description))

;; extract keys with a name
(let [{company-name :name
       address :location
       description :description} client]
  (println company-name address "-" description))

(let [{category :category} client]
  (println category))

;; default values
(let [{category :category, :or {category "Category not found"}} client]
  (println category))


;; multi level destructuring
(def multiplayer-game-state
  {:joe {:class "Ranger"
         :weapon "Longbow"
         :score 100}
   :jane {:class "Knight"
          :weapon "Greatsword"
          :score 140}
   :ryan {:class "Wizard"
          :weapon "Mystic Staff"
          :score 150}})

(let [{{:keys [class weapon]} :joe :ryan} multiplayer-game-state]
  (println "Joe is a" class "wielding a" weapon))

;; map can be destructured into map entries
(def bio  {:name "sathiya" :company "abc limited"})
(into [] bio)

(doseq [xs bio]
  (println xs))

(doseq [[k v] bio]
  (println "key: " k " value :" v))



