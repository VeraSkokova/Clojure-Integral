(ns clojure2.core-test
  (:require [clojure.test :refer :all]
            [clojure2.core :refer :all]))

(defn absolute-difference ^double [^double x ^double y]
  (Math/abs (double (- x y))))

(defn close? [tolerance x y]
  (< (absolute-difference x y) tolerance))

(deftest calculate-step-test
  (testing "Test step")
  (is (= (calculate-step 0. 1. 5) 0.2)))

(deftest calculate-first-term-test
  (testing "Test first term")
  (is (= (calculate-first-term square 0. 1.) 0.5)))

(deftest calc-test
  (testing "Test terms")
  (is (close? 1e-4 (calc square 0. 0.2 1. 0.2) 1.2)))

(deftest take-integral-test
  (testing "Test integral")
  (is (close? 1e-4 (take-integral square 0. 1. 5 0.2) 0.34)))