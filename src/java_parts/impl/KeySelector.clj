(ns java_parts.impl.KeySelector
  (:gen-class
    :name java_parts.impl.ClojuredKeySelector
    :implements [java_parts.defined_interfaces.IWikiKeySelector]))

(defn -getKey [this event]
  (.getUser event))
