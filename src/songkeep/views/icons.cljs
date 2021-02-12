(ns songkeep.views.icons
  "A wrapper around different icons. For the expo icons, view all the names on:
  https://icons.expo.fyi/"
  (:require ["@expo/vector-icons" :as icons]))

(defn search [props]
  [:> icons/Ionicons
   (assoc props :name "md-search")])


(defn caret-right [props]
  [:> icons/MaterialIcons
   (assoc props :name "keyboard-arrow-right")])


(defn books [props]
  [:> icons/MaterialCommunityIcons
   (assoc props :name "bookshelf")])


(defn numbered-list [props]
  [:> icons/MaterialCommunityIcons
   (assoc props :name "format-list-numbered")])
