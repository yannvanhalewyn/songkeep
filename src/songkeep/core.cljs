(ns songkeep.core
  (:require [reagent.core :as r]
            [shadow.expo :as expo]
            [songkeep.playground :as playground]))

(defn start ^:dev/after-load
  []
  (expo/render-root (r/as-element [playground/root])))

(defn init []
  (start))
