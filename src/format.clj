(ns teamz.format)

(defn- generate-empty-char-row [char length]
  "Generates a row of all one character"
  (->> char
       (repeat length)
       (string/join "")))

(defn format-for-display [team {:keys [spacing]}]
  "Uses various formatting options to present the final string..."
  (cond
    (nil? spacing)
    (interleave
      team
      (repeat
        (count team)
        (generate-empty-char-row "-" (count (first team)))))))