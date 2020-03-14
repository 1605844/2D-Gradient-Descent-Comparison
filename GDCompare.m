
f = @(x,y) (x-1)^2 + (y-5)^2 + x*y;
fcontour(f, [-6 6 -5 10])

hold on

line(GD(:,1),GD(:,2),'Color', 'Black');
line(SGD(:,1),SGD(:,2),'Color','Red');

