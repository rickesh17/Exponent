(ns reagentproject.db
  (:require [clojure.spec :as s]))

;; spec of app-db
(s/def ::greeting string?)
(s/def ::test1 string?)

(s/def ::app-db
  (s/keys :req-un [::greeting
                   ::test1]))

;; initial state of app-db
(def app-db {:greeting "Hi!"
             :test1 "Good!!!!"})