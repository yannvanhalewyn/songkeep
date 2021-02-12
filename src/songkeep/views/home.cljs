(ns songkeep.views.home
  (:require [songkeep.rn :as rn]
            [songkeep.views.icons :as icons]
            [songkeep.interop :as interop]
            [songkeep.proto-data :as proto-data]))


(defonce logo (js/require "../assets/icon.png"))
(defonce default-cover (js/require "../assets/img/default_cover_64.png"))


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



(defn- recent-tracks [{:keys [tracks]}]
  [rn/view
   ;; Header
   [rn/view {:style {:flex-direction :row
                     :justify-content :space-between
                     :align-items :center
                     :margin-bottom 12}}
    [rn/text {:style {:font-family "inter-black"
                      :font-size 16
                      :color "black"}}
     "Recently viewed"]

    [rn/touchable-opacity
     [rn/text {:style {:font-family "inter"
                       :font-size 16
                       :color "#33BAF4"}}
      "View all"]]]

   ;; Items
   (for [track tracks]
     ^{:key (:songkeep.track/name track)}
     [rn/view {:style {:margin-bottom 12}}
      [rn/touchable-native-feedback
       {:on-press #(.log js/console "Press" (:songkeep.track/name track))
        :background (rn/native-feedback-ripple "#efefef" false)}

       [rn/view {:style {:flex-direction :row
                         :justify-content :space-between
                         :align-items :center}}

        ;; Img and title
        [rn/view {:flex-direction :row
                  :align-items :center}
         [rn/image {:default-source default-cover
                    :source {:uri (:lastfm.coverart/small track)}
                    :style {:width 48
                            :height 48
                            :border-radius 8}}]
         [rn/view {:style {:margin-left 12}}
          [rn/text {:style {:font-family "inter-semibold"
                            :font-size 14
                            :color "#0F0F0F"}}
           (:songkeep.track/name track)]
          [rn/text {:style {:font-family "inter"
                            :font-size 12
                            :color "#151515"}}
           (:songkeep.artist/name track)]]]

        ;; Caret
        [rn/view
         [icons/caret-right
          {:size 20 :color "#555555"}]]]]])])


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
   [rn/view {:style {:padding 32}}
    [recent-tracks {:tracks (::proto-data/recent-tracks proto-data/DATA)}]]
   ;; Navigation
   ])
