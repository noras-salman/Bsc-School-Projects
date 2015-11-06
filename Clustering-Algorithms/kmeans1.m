function kmeans1( points,clusters )
%UNTITLED4 Summary of this function goes here
%   Detailed explanation goes here
[idx,ctrs] = kmeans(points,clusters);
X=points;
    plot(X(idx==1,1),X(idx==1,2),'r.','MarkerSize',20);
    hold on
plot(X(idx==2,1),X(idx==2,2),'b.','MarkerSize',20)
plot(ctrs(:,1),ctrs(:,2),'kx','MarkerSize',12,'LineWidth',2)
plot(ctrs(:,1),ctrs(:,2),'ko','MarkerSize',12,'LineWidth',2)
%legend('Cluster 1','Cluster 2','Centers')

end

