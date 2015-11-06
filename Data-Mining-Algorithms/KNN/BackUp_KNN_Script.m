clc;clear;
DataSet;
k=20;% number of neighours
n=size(D,1);%number of features
ASK=[youth medium yes fair];

dists=[];
for i=1:n-1
for j=i:n
    if i~=j
    dists(end+1,:)=[i j dist(D(i,:),D(j,:))];
    end;
end;
end;
dists
Nearest=[];
for i=1:k %k+1 not k (not counting the distance between it self
  [z,i]=min(dists(:,3));  
  Nearest(end+1,:)= [dists(i,1) dists(i,2)];
  dists(i,:)=[];    
end;
%Deleting it self from the Nighbours

fprintf('The closest %i Nighbours are \n',k);
Nearest