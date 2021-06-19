(ns clojure-bytes.set-map-invert
  (:require [clojure.set :as set]))

;; (map-invert m)

;; Returns the map with the vals mapped to the keys.
(set/map-invert {:a 1, :b 2})
;; {2 :b, 1 :a}

;; If there are duplicate keys, one is chosen:

(set/map-invert {:a 1, :b 1})
;; {1 :b}