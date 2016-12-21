(ns bridge.connectors.kafka
  (:import (java.util Properties)
           (org.apache.flink.streaming.connectors.kafka FlinkKafkaConsumer09 FlinkKafkaProducer09)))

(defn create-kafka-consumer [topic deserialization-schema properties]
  "Creates a Kafka data source."
  (FlinkKafkaConsumer09. topic deserialization-schema properties))

(defn create-kafka-producer [broker-address topic serialization-schema]
  "Creates a Kafka sink."
  (FlinkKafkaProducer09. broker-address topic serialization-schema))

(defn assign-timestamp-and-watermarks [consumer assigner]
  (.assignTimestampsAndWatermarks consumer assigner))

(defn create-properties [properties-vector]
  "Creates a java.util.Properties object. Property strings have to be passed as a vector-of-vectors or as a list-oflists,
  e.g.: [[key1 value1] [key2 value2]]"
  (loop [property-object (Properties.)
         remaining-proberties properties-vector]
    (if (empty? remaining-proberties)
      property-object
      (let [[current & remaining] remaining-proberties
            [key value] current]
        (.setProperty property-object key value)
        (recur property-object remaining)))))

