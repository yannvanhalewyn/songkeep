(ns songkeep.proto-data)

(defn make-track [song artist thumb]
  {:songkeep.track/name song
   :songkeep.artist/name artist
   :lastfm.coverart/small thumb})

(def DATA
  {::recent-tracks
   [(make-track "Talk" "Coldplay" "https://i.scdn.co/image/ab67616d0000b2734e0362c225863f6ae2432651")
    (make-track "Let it Happen" "Tame Impala" "https://i.scdn.co/image/ab67616d0000b2739e1cfc756886ac782e363d79")
    (make-track "The Pretender" "Foo Fighters" "https://i.scdn.co/image/ab67616d0000b27383e260c313dc1ff1f17909cf")
    (make-track "Sound of Silence" "Simon & Garfunkel" "https://i.scdn.co/image/ab67616d0000b273dd9e17a2000252d6b79cf904")]
   ::setlists
   [{:songkeep.setlist/name "Sister's birthday gig"
     :songkeep.repertoire/name "Nina en Yann"}
    {:songkeep.setlist/name "Livestream 23/02/21"
     :songkeep.repertoire/name "Nina en Yann"}
    {:songkeep.setlist/name "Open mic night @culDeSac"
     :songkeep.repertoire/name "Solo stuffs"}]
   ::repertoires
   [{:songkeep.repertoire/name "Nina en Yann"
     :songkeep.repertoire/song-count 26}
    {:songkeep.repertoire/name "Solo stuffs"
     :songkeep.repertoire/song-count 54}]})
