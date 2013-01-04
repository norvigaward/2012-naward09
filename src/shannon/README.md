Information calculator
========

This program sums all occurrences *and* calculates the sum of the O*ln(O) (O being the amount of occurrences).
*BEWARE*: the Java implementation of Math.log() uses base e. This gives the entropy in natural units, divide by ln(X) to get it in a custom base (X), e.g. ln(2) for binary

From there: H = ln(T) - LOGSUM/Ti

(equals H = ln(T) - 1/T * SUM(O * ln(O)))

The result is the information expressed in natural units (= 1/ln(2) ~ 1.44 bit)

An example output is;

	DIFF	1.843500288E9
	LOG	2.6730641522665957E12
	TOTAL	2.51758731142E11

And we can do some calculations with that info;

	Now H = ln(TOTAL) - LOG/TOTAL
	     = ln(2.51758731142×10^11) − (2.6730641522665957×10^12)÷(2.51758731142×10^11)
		  ~ 15.63417 Nat
		  = 22.55534 bit
		  = 2.819418 bit/character
	   (vs. 4.754887502 with regular coding)
