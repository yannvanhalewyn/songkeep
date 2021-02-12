(ns songkeep.views.icons
  "A wrapper around different icons. For the expo icons, view all the names on:
  https://icons.expo.fyi/"
  (:require ["@expo/vector-icons" :as icons]))

(defn search [props]
  [:> icons/Ionicons
   (assoc props :name "md-search")])