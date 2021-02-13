(ns songkeep.views.home
  (:require [songkeep.rn :as rn]
            [songkeep.interop :as interop]
            [songkeep.tailwind :refer [tw tw-clj get-color]]
            [songkeep.proto-data :as proto-data]
            [songkeep.views.icons :as icons]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Header

(defonce logo (js/require "../assets/icon.png"))


(defn- title-logo []
  [rn/view {:style (tw "flex-row items-center justify-center")}
   [rn/image {:source logo :style (assoc (tw-clj "absolute w-20 h-20") :left 12)}]
   [rn/text {:style (tw "text-3xl font-black text-white")}
    "SongKeep"]])


(defn- search-input []
  (let [[value set-value] (interop/use-state "")]
    [rn/view {:style (tw "flex-row items-center mt-6 p-3 bg-blue-400 rounded-lg")}
     [icons/search {:style (tw "mr-2 text-base text-blue-100")}]
     [rn/text-input {:value value
                     :on-change-text set-value
                     :auto-complete-type :off
                     :placeholder "Search for songs, artists, ..."
                     :placeholder-text-color (get-color "blue-100")
                     :selection-color (get-color "blue-500")
                     :style (tw "font-regular text-white text-base")}]]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Sections

(defonce default-cover (js/require "../assets/img/default_cover_64.png"))


(defn- section-header [{:keys [title style]}]
  [rn/view {:style (merge style (tw-clj "flex-row justify-between items-center"))}
   [rn/text {:style (tw "font-black text-base")}
    title]

   [rn/touchable-opacity
    [rn/text {:style (tw "font-regular text-sm text-blue-500")}
     "View all"]]])


(defn- recent-tracks-section [{:keys [tracks]}]
  [rn/view
   [section-header {:title "Recently viewed"
                    :style (tw-clj "mb-2")}]

   (for [track tracks]
     ^{:key (:songkeep.track/name track)}
     ;; If border desired on ripple, wrap in view with
     ;; {:border-radius 8 :overflow :hidden}
     [rn/touchable-native-feedback
      {:on-press #(.log js/console "Press" (:songkeep.track/name track))
       :background (rn/native-feedback-ripple (get-color "gray-200"))}

      [rn/view {:style (tw "flex-row justify-between items-center py-2")}

       ;; Img and title
       [rn/view {:style (tw "flex-row items-center")}
        [rn/image {:style (tw "w-12 h-12 rounded-lg")
                   :default-source default-cover
                   :source {:uri (:lastfm.coverart/small track)}}]
        [rn/view {:style (tw "ml-3")}
         [rn/text {:style (tw "font-semibold text-sm text-gray-900")}
          (:songkeep.track/name track)]
         [rn/text {:style (tw "font-regular text-xs text-gray-900")}
          (:songkeep.artist/name track)]]]

       ;; Caret
       [icons/caret-right {:style (tw "text-xl text-gray-700")}]]])])


(defn- setlists-section [{:keys [setlists]}]
  [rn/view {:style (tw "mt-6")}
   [section-header {:title "Setlists" :style (tw-clj "mb-1")}]

   (for [setlist setlists]
     ^{:key (:songkeep.setlist/name setlist)}
     [rn/touchable-native-feedback
      {:on-press #(.log js/console "Press" (:songkeep.setlist/name setlist))
       :background (rn/native-feedback-ripple (get-color "gray-200"))}
      [rn/view {:style (tw (str "flex-row justify-between items-center "
                                "py-3 border-b border-gray-200"))}

       ;; Name and setlist
       [rn/view
        [rn/text {:style (tw "font-semibold text-sm text-gray-900")}
         (:songkeep.setlist/name setlist)]
        [rn/view {:style (tw "flex-row items-center")}
         [icons/books {:style (tw "text-sm text-gray-900")}]
         [rn/text {:style (tw "font-regular text-sm text-gray-900 ml-1")}
          (:songkeep.repertoire/name setlist)]]]

       ;; Caret
       [icons/caret-right {:style (tw "text-xl text-gray-700")}]]])])


(defn- repertoires-section [{:keys [repertoires]}]
  [rn/view {:style (tw "mt-6")}
   [section-header {:title "Repertoires"
                    :style (tw-clj "mb-1")}]

   (for [repertoire repertoires]
     ^{:key (:songkeep.repertoire/name repertoire)}
     [rn/touchable-native-feedback
      {:on-press #(.log js/console "Press" (:songkeep.repertoire/name repertoire))
       :background (rn/native-feedback-ripple (get-color "gray-200"))}
      [rn/view {:style (tw (str "flex-row justify-between items-center "
                                "py-3 border-b border-gray-200"))}

       ;; Name and count
       [rn/view
        [rn/text {:style (tw "text-sm text-gray-900 font-semibold")}
         (:songkeep.repertoire/name repertoire)]
        [rn/view {:style (tw "flex-row items-center")}
         [icons/numbered-list {:style (tw "text-sm text-gray-900")}]
         [rn/text {:style (tw "ml-1 font-regular text-xs text-gray-900")}
          (:songkeep.repertoire/song-count repertoire) " songs"]]]

       ;; Caret
       [icons/caret-right {:style (tw "text-xl text-gray-700")}]]])])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Component

(defn component []
  (let [{::proto-data/keys [recent-tracks setlists repertoires]}
        proto-data/DATA]
    [rn/view {:style (tw "flex-1")} ;; For scroll view
     [rn/status-bar (tw-clj "bg-blue-500")]

     ;; Header (logo + search)
     [rn/view {:style (tw "px-8 py-6 rounded-b-lg bg-blue-500")}
      [title-logo]
      [:f> search-input]]

     ;; Sections
     [rn/scroll-view {:bounces true}
      [rn/view {:style (tw "p-8")}
       [recent-tracks-section {:tracks recent-tracks}]
       [setlists-section {:setlists setlists}]
       [repertoires-section {:repertoires repertoires}]]]

     ;; Navigation
     ]))
