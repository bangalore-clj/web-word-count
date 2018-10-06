(defproject wwc "0.1.0-SNAPSHOT"
  :description "Web Word Count - A CLI app"
  :url "https://github.com/bangalore-clj/web-word-count"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.4.1"]
                 [clj-http "3.9.1"]
                 [hickory "0.7.1"]]
  :main ^:skip-aot wwc.main
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
