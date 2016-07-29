(ns cycle.dom
  (:require-macros [hiccups.core :as h :refer [html]])
  (:require [hiccups.runtime :as hiccupsrt]
            [beicon.core :as b]))

(enable-console-print!)

(defn make-dom-driver [target]
  (fn [dom$]
    (do
      (b/subscribe dom$
                   #(let [template (html %)]
                      (set!
                       (.-innerHTML (.querySelector js/document target))
                       template))
                   #(js/console.error %)
                   #(println "done"))
      nil)))

