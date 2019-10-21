(ns clojure2.core
  (:gen-class))

(defn exp
  "Calculates e^x"
  [x n]
  (reduce * (repeat n x)))

(defn square
  [x]
  (* x x))

(defn calculate-step
  [a b n]
  (/ (- b a) n))

(defn calculate-first-term
  [f x0 xn]
  (* (+ (f x0) (f xn)) 0.5))

(defn calc
  [f a start end step]
  (loop [x start result 0] (if (>= x end)
                             result
                             (recur (+ x step) (+ result (f x))))))

(defn take-integral
  "Takes integral of function f"
  [f a b n h]
  (* h (+ (calculate-first-term f a b) (calc f a (+ a h) b h))))

(defn calc-memo
  [f a start end step]
  (memoize (calc f a start end step)))

(defn take-integral-memo
  "Memoized integral"
  [f a b n h]
  (memoize (fn [f a b n h] (* h (+ (calculate-first-term f a b) (calc-memo f a (+ a h) b h))))))

;;(time (calc square 0. 0.2 1. 0.2))
;;(time (calc-memo square 0. 0.2 1. 0.2))
;;(time (calc-memo square 0. 0.2 1. 0.2))

(println (time (take-integral square 0. 1. 5 (calculate-step 0. 1. 5))))
(println (time (take-integral-memo square 0. 1. 5 (calculate-step 0. 1. 5))))
(println (time (take-integral-memo square 0. 1. 5 (calculate-step 0. 1. 5))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ())
