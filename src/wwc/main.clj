(ns wwc.main
  (:require
    [wwc.cli  :as cli]
   [wwc.parse :as parse])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (if-let [{:keys [options
                   arguments]} (cli/cli-options args)]
    (-> (first arguments)
        (parse/fetch-webpage (:follow-redirects options))
        parse/parse-html
        println)
    (System/exit 1)))
