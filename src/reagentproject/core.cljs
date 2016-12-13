(ns reagentproject.core
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [reagentproject.handlers]
            [reagentproject.subs]))

(set! js/window.React (js/require "react"))
(def ReactNative (js/require "react-native"))

(def app-registry (.-AppRegistry ReactNative))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))

(defn alert [title]
      (.alert (.-Alert ReactNative) title))

(defn app-root []
  (let [greeting (subscribe [:get-greeting])
        test1 (subscribe [:get-test1])]
    (fn []
      [view {:style {:flex-direction "column" :margin 10 :align-items "right"}}
       [image {:source (js/require "./assets/images/cljs.png")
               :style {:width 200
                       :height 200}}]
       [text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} @greeting]
       [touchable-highlight {:style {:background-color "#999" :padding 10 :border-radius 5}
                             :on-press #(alert "Yo Yo Yo!")}
        [text {:style {:color "white" :text-align "center" :font-weight "bold"}} "press me"]]
       [text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} @test1]]
      )))

(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent app-registry "main" #(r/reactify-component app-root)))
