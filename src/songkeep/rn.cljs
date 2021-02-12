(ns songkeep.rn
  (:require [reagent.core :as reagent]
            ["react-native" :as rn]))

(def view (reagent/adapt-react-class rn/View))
(def text (reagent/adapt-react-class rn/Text))
(def image (reagent/adapt-react-class rn/Image))
(def text-input (reagent/adapt-react-class rn/TextInput))
(def scroll-view (reagent/adapt-react-class rn/ScrollView))

(def flat-list (reagent/adapt-react-class rn/FlatList))

(def touchable-highlight (reagent/adapt-react-class rn/TouchableHighlight))
(def touchable-native-feedback (reagent/adapt-react-class rn/TouchableNativeFeedback))
(def touchable-opacity (reagent/adapt-react-class rn/TouchableOpacity))
(def status-bar (reagent/adapt-react-class rn/StatusBar))

(def native-feedback-ripple (.-Ripple rn/TouchableNativeFeedback))
