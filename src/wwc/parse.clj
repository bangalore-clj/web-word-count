(ns wwc.parse
  (:require
   [clojure.pprint :as pp]
   [clojure.string :as string]
   ;;
   [clj-http.client :as http]
   [hickory.core :as hickory]))


(defn fetch-webpage
  [url follow-redirects?]
  (-> url
      (http/get (if follow-redirects? {} {:max-redirects 0}))
      (get :body)))


(defn count-words
  [text]
  (->> (string/split text #"\s+")
       (mapcat #(string/split % #"\."))
       (filter seq)
       (mapcat #(string/split % #"\,"))
       (filter seq)
       count))


(defn count-text
  [hiccup-data]
  (cond
    (map? hiccup-data)    0
    (coll? hiccup-data)   (->> hiccup-data
                               (map count-text)
                               (reduce + 0))
    (string? hiccup-data) (count-words hiccup-data)
    :otherwise            0))


(defn parse-html
  [html]
  (let [parsed (hickory/parse html)
        hiccup (hickory/as-hiccup parsed)]
    (count-text hiccup)))
