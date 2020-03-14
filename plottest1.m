A = table2array(test1S1);

f = @(x,y) (x - y)^2 - 2*x - 28*y +197;
fcontour(f, [-30 30 -30 30])
hold on
for i = 1:size(A,1)
    plot(A(i,1), A(i,2),'bo', 'linewidth', 5)
end

