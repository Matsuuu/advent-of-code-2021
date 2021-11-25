(ns advent-of-code.core
  (:gen-class)
  (:require
   [clojure.java.io :as io]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn get-file
  ([file]
   (slurp file))
  ([file split-lines]
   (if split-lines
     (line-seq (io/reader file))
     (slurp file))))

(defn parse-long [long-str]
  (long (Float/valueOf long-str)))

