(ns wwc.cli
  (:require
    [clojure.pprint :as pp]
    [clojure.tools.cli :as ctc]))


(def all-options
  [["-f" "--follow-redirects" "Follow redirects to reach page"]
   ["-h" "--help" "Show help/usage text"]])


(defn print-usage
  []
  (println "Usage:   java -jar wwc.jar [options] web-url")
  (println "Example: java -jar wwc.jar http://wikipedia.com")
  (println "Options:")
  (->> all-options
       (mapv #(zipmap ["Short option"
                       "Long option"
                       "Description"] %))
       pp/print-table))


(defn print-errors-and-usage
  [errors]
  (println "Errors:")
  (doseq [each errors]
    (println "  " each))
  (println)
  (print-usage))


(defn cli-options
  "Given CLI arguments (vector of string tokens), return options on success.
  On input error, print usage and return nil."
  [args]
  (let [{:keys [arguments
                errors
                options]
         :as result} (ctc/parse-opts args all-options)]
    (cond
      (seq errors)       (print-errors-and-usage errors)
      (:help options)    (print-usage)
      (empty? arguments) (print-errors-and-usage ["Missing web-url argument"])
      :otherwise         result)))
