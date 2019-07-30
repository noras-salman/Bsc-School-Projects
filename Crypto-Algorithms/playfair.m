%% Playfair Cipher By Noras Salman 

clc
clear


%input
keyword='keyword';
plainText='secretmessages';

%% making the polybius square
%initlize
for i=1:1:5
    for j=1:1:5
   polySeq(i,j)='#';
    end;
end;
% replace j with if found in keyword
for i=1:1:length(keyword)
if keyword(i)=='j'
   keyword(i)='i';
end;
end;
%% make square
k=1;
i=1;
j=1;
while k<=length(keyword)
    if j>5
        j=1;i=i+1;
    end;
   if isempty(find(polySeq==keyword(k)))
   polySeq(i,j)=keyword(k);
   k=k+1;j=j+1;
   else
   k=k+1;
   end;
end;
polySeq
%fill with the rest
for alpha='a':'i'
    if j>5
        j=1;i=i+1; % i & j is taken from the  step before
    end;
  if isempty(find(polySeq==alpha))
   polySeq(i,j)=alpha;
   j=j+1;
   end;  
end;
for alpha='k':'z'
    if j>5
        j=1;i=i+1; % i & j is taken from the  step before
    end;
  if isempty(find(polySeq==alpha))
   polySeq(i,j)=alpha;
   j=j+1;
   end;  
end;    

 polySeq   
    
%making diagrafs
diagrafs=[];
i=1;
inserted=0;%num of force inserted items
while i<=length(plainText)
  if ((i==length(plainText))&& (mod(i+inserted,2)==1)) 
  diagrafs=[diagrafs ;plainText(i) 'x'];%force insert at the end
  i=i+1;
  inserted=inserted+1;
  elseif plainText(i)==plainText(i+1)
 diagrafs=[diagrafs; plainText(i) 'x'];%force insert if repeated
 i=i+1;
 inserted=inserted+1;
else
 diagrafs=[diagrafs ;plainText(i) plainText(i+1)] ;
 i=i+2;
end;  
end;
diagrafs
%==encrypt
[n,m]=size(diagrafs);
cipherText=[];
for i=1:1:n
   [i1,j1]=find(polySeq==diagrafs(i,1));
   [i2,j2]=find(polySeq==diagrafs(i,2));
    
  if i1==i2   
  cipherText=[cipherText polySeq(i1,mod(j1,5)+1) polySeq(i2,mod(j2,5)+1) ];    
  elseif j1==j2
  cipherText=[cipherText polySeq(mod(i1,5)+1,j1) polySeq(mod(i2,5)+1,j2) ];    
  else 
    cipherText=[cipherText polySeq(i1,j2) polySeq(i2,j1) ];    
    
  end; 
    
end; 
cipherText
    