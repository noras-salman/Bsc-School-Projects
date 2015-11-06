clc;clear;
DataSet;
k=3;% number of neighours
n=size(D,1);%number of features
ASK=[youth medium yes fair];

dists=[];

for i=1:n
    
    dists(end+1,:)=[i dist(D(i,:),ASK)];
   
end;

dists
Nearest=[];
for i=1:k %k+1 not k (not counting the distance between it self
  [z,i]=min(dists(:,2));
  Nearest(end+1)= [dists(i,1)];
  dists(i,:)=[];    
end;
%Deleting it self from the Nighbours

fprintf('The closest %i Nighbours are \n',k);
Nearest
clasify=y(Nearest)'
if sum(clasify)>sum(clasify==0)
 fprintf('The Class is Classified as YES \n');
else
 fprintf('The Class is Classified as NO \n');
end;