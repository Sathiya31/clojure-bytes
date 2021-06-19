(ns clojure-bytes.set-join
  (:require [clojure.set :as set :refer :all]))

;; join

;; (join xrel yrel)(join xrel yrel km)

;; When passed 2 rels, returns the rel corresponding to the natural
;; join. When passed an additional keymap, joins on the corresponding
;; keys.

(def first-relation #{{:a 1} {:a 2}})
(def second-relation #{{:b 1} {:b 2}})

;; join without condition. it does natural join
;; it joins all the values in one map to every value in second map
(join first-relation second-relation)
;#{{:b 1, :a 1}
;  {:b 2, :a 1}
;  {:b 1, :a 2}
;  {:b 2, :a 2}}

;; the keymap is like a join condition. it combines those records only those match the condition
;; its like a where clause in the join
(join first-relation second-relation {:a :b})
;;=> #{{:a 1, :b 1} {:a 2, :b 2}}

(def third-relation #{{:b 1} {:b 3}})

;; the final result will be like a table join with matching where conditions
(join first-relation third-relation {:a :b})
;;=> #{{:a 1, :b 1}}

;; A more practical example

(def animals #{{:name "betsy" :owner "brian" :kind "cow"}
               {:name "jake" :owner "brian" :kind "horse"}
               {:name "josie" :owner "dawn" :kind "cow"}})

(def personalities #{{:kind "cow" :personality "stoic"}
                     {:kind "horse" :personality "skittish"}})

;; The join combines the maps using the common key ':kind'
(join animals personalities)
;; #{{:kind "horse", :personality "skittish", :name "jake", :owner "brian"}
;  {:kind "cow", :personality "stoic", :name "betsy", :owner "brian"}
;  {:kind "cow", :personality "stoic", :name "josie", :owner "dawn"}}

(def personalities-with-different-key #{{:species "cow" :personality "stoic"}
                                        {:species "horse" :personality "skittish"}})

;; When there is no common key it does natural join
(join animals personalities-with-different-key)
;; #{{:species "horse", :personality "skittish", :name "betsy", :owner "brian", :kind "cow"}
;  {:species "horse", :personality "skittish", :name "jake", :owner "brian", :kind "horse"}
;  {:species "horse", :personality "skittish", :name "josie", :owner "dawn", :kind "cow"}
;  {:species "cow", :personality "stoic", :name "jake", :owner "brian", :kind "horse"}
;  {:species "cow", :personality "stoic", :name "betsy", :owner "brian", :kind "cow"}
;  {:species "cow", :personality "stoic", :name "josie", :owner "dawn", :kind "cow"}}

;; We can pass key map to let it know which key should map with which one
(join animals personalities-with-different-key {:kind :species})
;; #{{:species "horse", :personality "skittish", :name "jake", :owner "brian", :kind "horse"}
;  {:species "cow", :personality "stoic", :name "betsy", :owner "brian", :kind "cow"}
;  {:species "cow", :personality "stoic", :name "josie", :owner "dawn", :kind "cow"}}