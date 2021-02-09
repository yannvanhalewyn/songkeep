(ns songkeep.core
  (:require ["react-native" :as rn]
            ["react" :as react]
            [reagent.core :as r]
            [shadow.expo :as expo]))

(def styles
  {:container {:flex 1
               :background-color :white
               :align-items :center
               :justify-content :flex-start
               :padding-top 50}
   :button {:background-color :blue
            :border-radius 12
            :color :white
            :marginTop 32}
   :button-text {:font-weight :bold
                 :paddingVertical 6
                 :paddingHorizontal 12
                 :color :white
                 :font-size 20}})

(defn counter []
  (let [[count set-count] (react/useState 0)]
    [:> rn/View {:style {:flex 1 :align-items :center}}
     [:> rn/Text {} (str "Count: " count)]
     [:> rn/TouchableOpacity {:style (:button styles)
                              :on-press #(set-count (inc count))}
      [:> rn/Text {:style (:button-text styles)}
       "Click me!"]]]))

(defn root []
  [:> rn/View {:style (:container styles)}
   [:> rn/Text {} "Hello"]
   [:f> counter]])

(defn start ^:dev/after-load
  []
  (expo/render-root (r/as-element [root])))

(defn init []
  (start))
