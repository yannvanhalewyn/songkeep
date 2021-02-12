(ns songkeep.views.home
  (:require [songkeep.rn :as rn]
            [songkeep.views.icons :as icons]
            [songkeep.interop :as interop]
            [songkeep.proto-data :as proto-data]))


(defonce logo (js/require "../assets/icon.png"))


(defn- title-logo []
  [rn/view {:style {:flex-direction :row
                    :align-items :center
                    :justify-content :center}}
   [rn/image {:source logo :style {:position :absolute :left 12
                                   :width 80 :height 80}}]
   [rn/text {:style {:font-family "inter-black"
                     :font-size 32
                     :color "white"}}
    "SongKeep"]])

(defn- search-input []
  (let [[value set-value] (interop/use-state "")]
    [rn/view {:style {:flex-direction :row
                      :align-items :center
                      :background-color "#33BAF4"
                      :border-radius 8
                      :margin-top 24
                      :padding 12}}
     [icons/search {:size 16 :color "#C6EEFF" :style {:margin-right 8}}]
     [rn/text-input {:value value
                     :on-change-text set-value
                     :auto-complete-type :off
                     :placeholder "Search for songs, artists, ..."
                     :placeholder-text-color "#C6EEFF"
                     :selection-color "#0FAAEC"
                     :color "white"
                     :font-family "inter"
                     :font-size 16}]]))


(defn home []
  [rn/view
   [rn/status-bar {:background-color "#0FAAEC"}]

   ;; Header (logo + search)
   [rn/view {:background-color "#0FAAEC"
             :style {:padding 32
                     :border-bottom-left-radius 8
                     :border-bottom-right-radius 8}}
    [title-logo]
    [:f> search-input]]

   ;; Sections
   ;; Navigation
   ])
