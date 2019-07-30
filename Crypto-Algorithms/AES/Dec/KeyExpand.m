function [ Out ] = KeyExpand( In )
Round=0;
Out=In;
for i=4:43
if mod(i,4)==0
Round=Round+1;   
wi=wordSubByte_Norm(RootWord(Word(Out,i-1)));
wi=bitxor(wi,Word(Out,i-4));
Out=[Out bitxor(wi,Rcon(Round))];
else
 Out=[Out bitxor(Word(Out,i-1),Word(Out,i-4))];   
end;
end

temp=Out
tempFinal=[];
for i=1:10    
tempFinal(:,i*4-3:i*4)=temp(:,end-3:end);
temp(:,end-3:end)=[];
end;  
tempFinal(:,end+1:end+4)=In;
Out=tempFinal
end

