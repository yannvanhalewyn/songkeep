(ns songkeep.core
  (:require ["react-native" :as rn]
            ["react" :as react]
            ["expo-constants" :as constants]
            [reagent.core :as r]
            [shadow.expo :as expo]))

(def STATUS_BAR_HEIGHT (.. constants -default -statusBarHeight))

(def styles
  {:container {
               :flex 1
               :background-color :white
               :align-items :center
               :justify-content :flex-start
               :margin-top STATUS_BAR_HEIGHT
               }
   :button {:background-color "#460392"
            :border-radius 12
            :color :white
            :marginTop 12}
   :button-text {:font-weight :bold
                 :color :white
                 :paddingVertical 6
                 :paddingHorizontal 12
                 :font-size 16}})

(def TEXT "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras vel interdum enim. Aenean non nulla ante. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris egestas tristique quam, posuere ultrices odio lobortis vel. Nunc et neque at odio ornare porttitor. Morbi venenatis velit id aliquam congue. Curabitur ultricies orci ex, a tincidunt ligula fringilla et. Donec non est vitae quam iaculis convallis. Vivamus auctor tellus libero, quis pharetra diam fringilla at. Donec ultricies metus et turpis mattis pharetra. Pellentesque velit ante, rutrum id metus sed, tristique interdum turpis. Aenean convallis, ligula vitae lacinia euismod, massa odio condimentum sem, sed imperdiet diam felis et elit.

Morbi euismod dolor metus, non vestibulum ligula venenatis sit amet. Suspendisse vestibulum euismod molestie. Nam nec lobortis orci. Maecenas fringilla nisl ac laoreet consectetur. Nam auctor nunc a nisl elementum, nec tempor leo molestie. Mauris vitae aliquam ante. Morbi sit amet urna quis nulla lobortis dapibus et ut ex. Quisque feugiat faucibus urna, at rhoncus nisi blandit eu. In commodo mi consequat facilisis posuere. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.

Curabitur blandit metus id sapien vehicula efficitur. Ut consectetur sapien lectus, vitae cursus felis luctus in. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Mauris a tristique arcu. Aenean aliquam blandit pellentesque. Duis varius arcu ac enim facilisis, eget accumsan est interdum. Cras dictum diam et lacinia venenatis. Sed eu rutrum diam. Etiam vel aliquam mauris. Etiam eget libero varius, sollicitudin tellus sit amet, placerat tortor. Curabitur id orci hendrerit est iaculis ornare sit amet quis neque. Proin pellentesque ornare quam rhoncus dictum. Aenean feugiat vel neque non venenatis. Maecenas lacinia mauris consequat mi fermentum tempor. Aliquam accumsan, mi sed semper pellentesque, tortor dolor blandit dolor, ut vestibulum massa est non libero. Etiam vestibulum finibus volutpat.

Integer eget imperdiet elit. Proin sem elit, tempor at hendrerit vitae, euismod in magna. Suspendisse vel bibendum arcu. Ut risus odio, fringilla nec tempus maximus, pharetra et ante. In hac habitasse platea dictumst. In in molestie eros. Nam ultrices massa erat, quis tincidunt odio eleifend id. Nunc sed sem leo.

Mauris at orci pellentesque, fermentum risus in, fermentum magna. Nulla ultrices vestibulum magna vehicula sollicitudin. Aliquam arcu nibh, malesuada at accumsan et, maximus nec odio. Vestibulum et mi ac nunc egestas dignissim. Aliquam erat volutpat. Integer tellus erat, cursus vitae vestibulum eu, fringilla ac turpis. Praesent eget suscipit ligula. Suspendisse vel ultricies eros, vitae semper massa. Nulla elit velit, semper at laoreet ac, rhoncus dictum ex. Fusce erat nibh, dignissim non nunc at, fermentum lacinia ipsum. Nunc ultrices a enim id convallis. Quisque luctus at felis eget maximus. Donec ornare est nec risus posuere sagittis. Vestibulum eleifend odio turpis, ac suscipit est interdum id. Morbi accumsan massa ipsum, at dapibus risus accumsan vel.")

(defn counter []
  (let [[count set-count] (react/useState 0)]
    [:> rn/View {:style {:flex 1 :align-items :center}}
     [:> rn/Text {:style {:color "#888" :font-size 18}}
      (str "Count test2: " count)]
     [:> rn/TouchableOpacity {:style (:button styles)
                              :on-press #(set-count (inc count))}
      [:> rn/Text {:style (:button-text styles)}
       "Count me foobar!"]]

     [:> rn/ScrollView
      [:> rn/Text {:style {:padding-horizontal 12 :padding-vertical 24}} TEXT]]]))

(defn root []
  [:> rn/SafeAreaView {:style (:container styles)}
   [:> rn/Text {} "Hello"]
   #_[:> rn/Image {:source ""}]
   [:f> counter]])

(defn start ^:dev/after-load
  []
  (expo/render-root (r/as-element [root])))

(defn init []
  (start))
