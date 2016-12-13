(ns java_parts.impl.FoldFunction
  (:gen-class
    :name java_parts.impl.ClojuredFoldFunction
    :implements [java_parts.defined_interfaces.IWikiFoldFunction]))

(defn -fold [this acc event]
  (set! (. acc f0) (.getUser event))
  (set! (. acc f1) (+ (.f1 acc) (.getByteDiff event)))
  acc)