(ns songkeep.tailwind
  (:require-macros [shadow.resource :refer [inline]])
  (:require ["tailwind-rn" :as tw]
            [goog.object :as obj]))


(defn adapt-font-weights
  "Makes it so we can use tailwind's 'font-bold' etc.. utilities within our
  project where those weights are font families instead."
  [styles]
  (doseq [[class rules] {"font-regular" {:fontFamily "inter"}
                         "font-bold" {:fontFamily "inter-bold"}
                         "font-semibold" {:fontFamily "inter-semibold"}
                         "font-black" {:fontFamily "inter-black"}}]
    (obj/set styles class (clj->js rules)))
  styles)


;; TODO maybe parse in macro so it doesn't parse the json each startup? Or at
;; least check that the production build pre-compiles the javascript. Actually I
;; heard using the JSON parser may be faster than parse pre-compiled javascript
;; so verify this.
;;
;; I actually think since we have powerful macro's here, we should make a
;; similar implementation in clojure. First check if the goog compiler inlines
(let [tw-context (-> (.parse js/JSON (inline "styles.json"))
                     (adapt-font-weights)
                     (tw/create))]
  (def tw (.-tailwind tw-context))
  (def tw-clj (comp js->clj tw))
  (def get-color (.-getColor tw-context)))
