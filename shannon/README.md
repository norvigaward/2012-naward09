Information calculator
========

This program sums all occurrences *and* calculates the sum of the O*ln(O) (O being the amount of occurrences).

From there: H = log(T) - LOGSUM/T

(equals H = log(T) - 1/T * SUM(O * ln(O)))

The result is the information expressed in natural units (= 1/ln(2) ~ 1.44 bit)

An example output is;
	cat outData_calcShan/part-r-00000 
	LOG	   743932.7735045124
	TOTAL  2.12026857E8
	
	Now H = ln(TOTAL) - (1/TOTAL)*LOG
		  = 8.322882203 Nat
		  = 12.00738088 bit
		  = 2.401476176 bit/character
	   (vs. 4.754887502 with regular coding)
	   
	   
##TODO's
* Get better precision (replace double)