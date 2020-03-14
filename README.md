The code and plotting tools used to make Figure 2.1.

Java Classes:
- Bilinear: Called bilinear because I originally wanted to apply GD to a bilinear form, but the final function did not turn out to be one!
            Contains both the gradient and stochastic gradient descent algorithms, and writes each step into a text file.
 - Point:   The point object used in bilinear.
 - Polynomial:  Standard polynomial class, not used in the final bilinear class.
 
 Matlab:
 - GD Compare: The code used to plot the figure itself, using the data created from bilinear.
