(ns cycle.core
  (:require [beicon.core :as b]))

(defn make-sink-proxies [drivers]
  (into {} (for [[k v] drivers] [k (b/bus)])))

(defn run [main-fn drivers]
  (let [proxy-sources (make-sink-proxies drivers)
        sinks (main-fn proxy-sources)]
    (doseq [dk (keys drivers)]
      (when-let [s ((get drivers dk) (get sinks dk))]
        (b/subscribe s #(b/push! (get proxy-sources dk) %))))))
