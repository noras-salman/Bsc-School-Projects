function  average1( points )
%UNTITLED Summary of this function goes here

clc;
Destant=pdist(points,'euclidean');
Calcu=linkage(Destant,'average');
[H,T] = dendrogram(Calcu,'colorthreshold','default');
set(H,'LineWidth',2);
Xlabel('Points          [P1,P2,....Pn]');

end

