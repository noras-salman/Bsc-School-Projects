
clc;
clear;

DataSet;

X=[youth medium yes fair];
n=size(D,1);% number of  examples in Data set
m=size(D,2);% number of features in data set
P=[];
info=0;
for featureIndex=1:m
 u=unique(D(:,featureIndex));
  for X=1:length(u)
    for yes_no=0:1
     
 nc=length(find(and(D(:,featureIndex)==u(X),y==yes_no)));
 P=[P; featureIndex yes_no nc/n]; 
 info=info+nc/n*log(nc/n);
 end;
 end;
 info=-info
end;
P