(ns clojure-bytes.set-rename)

;; rename-keys

;; (rename-keys map kmap)

;; Returns the map with the keys in kmap renamed to the vals in kmap
(set/rename-keys {:a 1, :b 2} {:a :new-a, :b :new-b})
;; => {:new-a 1, :new-b 2}

;; rename

;; (rename xrel kmap)

;; Returns a rel of the maps in xrel with the keys in kmap renamed to the vals in kmap

(def relation #{  {:a 1, :b 1}  {:a 2, :b 2} })

(set/rename relation {:a :new-a})
;; => #{{:b 1, :new-a 1} {:b 2, :new-a 2}}

(set/rename relation {:a :new-a :b  :new-b})
;; => #{{:new-a 2, :new-b 2} {:new-a 1, :new-b 1}}
