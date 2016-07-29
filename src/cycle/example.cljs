(ns cycle.example
  (:require [beicon.core :as b]
            [cycle.core :refer [run]]
            [cycle.dom :refer [make-dom-driver]]
            [cycle.log :refer [make-log-driver]]
            [devtools.core :as devtools]))

(devtools/install!)

(defn main [sources]
  (let [msg (b/interval 1000)
        vdom (->> (b/interval 1000)
                  (b/map (fn [i] [:div (str "hello " i)])))]
    {:log msg
     :dom vdom}))

(def drivers
  {:log (make-log-driver)
   :dom (make-dom-driver "#app")})

(run main drivers)
