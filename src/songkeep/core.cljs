(ns songkeep.core
  (:require [reagent.core :as r]
            [shadow.expo :as expo]
            ["@expo-google-fonts/inter" :as font]
            [songkeep.views.home :as home-view]
            [songkeep.rn :as rn]))

(defn root []
  ;; https://directory.now.sh/ to search for which imports are needed.
  (let [[fonts-loaded] (font/useFonts
                        (clj->js {:inter-black font/Inter_900Black
                                  :inter-bold font/Inter_700Bold
                                  :inter-semibold font/Inter_600SemiBold
                                  :inter font/Inter_400Regular}))]
    (if fonts-loaded
      ;; TODO use safeareaview
      [home-view/home]
      [rn/text "Loading Fonts.."])))

(defn start ^:dev/after-load
  []
  (expo/render-root (r/as-element [:f> root])))

(defn init []
  (start))
