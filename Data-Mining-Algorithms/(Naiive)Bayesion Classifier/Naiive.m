function [ probability ] = Naiive(D, y ,X )

yes=1;
no=0;

pYes=P(y,1,yes);
pNo=P(y,1,no);
for i=1:length(X)
nc=LablasCorrection(D,X,y,i,yes);
n=length(find(y==yes));
pYes=pYes*(nc/n);
nc=LablasCorrection(D,X,y,i,no);
n=length(find(y==no));
pNo=pNo*(nc/n);
end;

answer=[pNo pYes]
[n,i]=max(answer);
probability=i-1;
if probability==0
 fprintf('X is Classified as NO \n');  
else
 fprintf('X is Classified as YES \n'); 
end;
end



