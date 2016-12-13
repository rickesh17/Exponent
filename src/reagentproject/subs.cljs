(ns reagentproject.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]))

(register-sub
  :get-greeting
  (fn [db _]
    (reaction
      (get @db :greeting))))

(register-sub
  :get-test1
  (fn [db _]
    (reaction
      (get @db :test1))))
