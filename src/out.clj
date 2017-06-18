(ns teamz.out)

(defn- pad-string [string pad-char]
  "Fundamental operation: pad a string
   with choice character..."
  (str pad-char string pad-char))

(defn- padded-enough? [width]
  "Stupidly redundant way of asking if padded string
   is equal to desired width or one off for odd spacing..."
  (not
    (nil? (some #(= width %) [0 1]))))

(defn- longest-string-length [string-coll]
  "Takes collection of strings and returns
   length of longest string..."
  (apply max (map #(count %) string-coll)))

(defn pad-string-to-length [length char string]
  "Recursively pad a name with a separator character
   until it is a desired length..."
  (loop [padded string]
    (let [pad-distance (- length (count padded))]
      (if-not (padded-enough? pad-distance)
        (recur (pad-string padded char))
        padded))))

(defn pad-all-strings-equally [string-collection]
  "Takes collection of strings and pads appropriate length...
  Returns a lazy seq..."
  (map
    (partial pad-string-to-length (longest-string-length string-collection) "-")
    string-collection))

(defn players->row [players]
  "Takes players in a row and formats a string
   so that they are adequetely spaced..."
  (clojure.string/join "" (pad-all-strings-equally players)))

(defn rows->team-string [row-strings]
  "Formats the output representation of the team...
   Assumes that rows are of varying lengths and need formatting..."
  (pad-all-strings-equally row-strings))

