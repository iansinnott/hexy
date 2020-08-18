(ns hexy.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

;; - read bytes from a file
;;   - slurp-bytes
;; - reduce bytes, formatting into hex

;; So, what we're doing here seems a bit verbose but it is java interop after all
;; 1. Take a string and coercie it into a java.io.InputStream
;;    (Not sure what implications come along with InputStrams)
;; 2. Initialize a new ByteArrayOutputStream (Empty at this point)
;; 3. Copy the input stream data to the output stream.
;; 4. Finally, use the toByteArray method to get a byte array
(defn slurp-bytes [^String filepath]
  (try
    (with-open [instream (io/input-stream filepath)
                outstream (new java.io.ByteArrayOutputStream)]
      (io/copy instream outstream)
      (.toByteArray outstream))
    (catch java.io.FileNotFoundException e
      (println (str "File not found: " (.getMessage e))))))

;; Two spaces. Takes up the right amount of space to serve as a placeholder for
;; a hex num
(def placeholder "  ")

;; Pat a two digits
(def fmt-hex (partial format "%02x"))

;; Not sure why this is padded at 7 digits, but that's how hexdump does it.
(def fmt-hex-col (partial format "%07x"))

(defn hexy
  "Return a hexdump for the file at filepath. Format is seq of output rows"
  [^String filepath]
  (let [bs (slurp-bytes filepath)
        hex-byte-strings (->> bs
                              (map fmt-hex)
                              (partition 16 16 (repeat placeholder))
                              (map (partial s/join " "))
                              (map-indexed (fn
                                             [i, x]
                                             (str (fmt-hex-col (* i 16)) " " x))))]
    (concat
     hex-byte-strings
     (list (fmt-hex-col (count bs))))))

(defn -main
  "DO stuff"
  [& args]
  (println (s/join "\n" (hexy (first args)))))


(comment
  ;; Compare that str and cons lead to very differnet outcomes
  (map (partial str "HEY") '("hey" "there"))
  (map (partial cons "HEY") '("hey" "there"))
  (-main "./hello.txt"))

(comment
  ;; Experimenting with the formatter. The java docs are _very_ sparse on examples.
  (->> (range 3 100 10)
       (map (partial format "%02x"))))

(comment
  ;; Can get the contents of a dir like this
  (take Math (map str (file-seq (io/as-file "."))))
  (-main "./hello.txt"))
