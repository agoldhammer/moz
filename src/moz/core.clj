(ns moz.core
  (:require [oz.core :as oz])
  (:gen-class))

(defn play-data [& names]
  (for [n names
        i (range 20)]
    {:time i :item n :quantity (+ (Math/pow (* i (count n)) 0.8) (rand-int (count n)))}))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def line-plot
  {:data {:values (play-data "monkey" "slipper" "broom")}
   :encoding {:x {:field "time"}
              :y {:field "quantity"}
              :color {:field "item" :type "nominal"}}
   :mark "line"})

(def stacked-bar
  {:data {:values (play-data "munchkin" "witch" "dog" "lion" "tiger" "bear")}
   :mark "bar"
   :encoding {:x {:field "time"
                  :type "ordinal"}
              :y {:aggregate "sum"
                  :field "quantity"
                  :type "quantitative"}
              :color {:field "item"
                      :type "nominal"}}})

#_(oz/view! stacked-bar)

(defn get-proj-dir
  []
  (str (System/getProperty "user.dir") "/"))

(defn read-data-file
  [fname]
  (oz/load (str (get-proj-dir) fname ".edn")))

#_(def avg-stats-plot
  {:data {:values (read-data-file "stats")}
  :mark "point"
  :encoding {
             :x {:field "path" :type "quantitative"}
             :y {:field "avg-speedup" :type "quantitative"}
             :color {:bin true :field "size" :type "quantitative"}}})

#_(def avg-time-plot
  {:data {:values (read-data-file "stats")}
   :mark "point"
   :encoding {:x {:field "path" :type "quantitative"}
              :y {:field "avg-time" :type "quantitative"}
              :color {:bin true :field "size" :type "quantitative"}}})


(defn stats-plot
  []
  (oz/view!
   {:data {:values (read-data-file "stats")}
    :mark "point"
    :encoding {:x {:field "path" :type "quantitative"}
               :y {:field "speedup" :type "quantitative"}
               :color {:bin true :field "size" :type "quantitative"}}}))

(defn time-plot
  []
  (oz/view!
   {:data {:values (read-data-file "stats")}
    :mark "point"
    :encoding {:x {:field "path" :type "quantitative"}
               :y {:field "time" :type "quantitative"}
               :color {:bin true :field "size" :type "quantitative"}}}))

