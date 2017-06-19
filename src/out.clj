(ns teamz.out)

; comment test

(defn pad-string-to-length [length char string]
  "Recursively pad a name with a separator character
   until it is a desired length..."
  (letfn [(pad-string [str ch] (str ch str ch))
          (padded-enough? [dist] (<= 0 dist 2))]
    (loop [padded string]
      (let [pad-distance (- length (count padded))]
        (if-not (padded-enough? pad-distance)
          (recur (pad-string padded char))
          padded)))))

(defn pad-all-strings-equally [string-collection]
  "Takes collection of strings and pads appropriate length...
  Returns a lazy seq..."
  (letfn [(longest-string-length [col] (apply max (map #(count %) col)))]
    (map
      (partial pad-string-to-length (longest-string-length string-collection) "-")
      string-collection)))

(defn players->row [players]
  "Takes players in a row and formats a string
   so that they are adequetely spaced..."
  (clojure.string/join "" (pad-all-strings-equally players)))

(defn rows->team-string [row-strings]
  "Formats the output representation of the team...
   Assumes that rows are of varying lengths and need formatting..."
  (pad-all-strings-equally row-strings))

