(ns cycle.log
  (:require [beicon.core :as b]))

(defn make-log-driver []
  (fn [msg$]
    (do
      (b/subscribe msg$
                   #(js/console.log %))
      nil)))
