function [ Out ] = KeyExpand( In )
Round=0;
Out=In;
for i=4:43
if mod(i,4)==0
Round=Round+1;   
wi=wordSubByte(RootWord(Word(Out,i-1)));
wi=bitxor(wi,Word(Out,i-4));
Out=[Out bitxor(wi,Rcon(Round))];
else
 Out=[Out bitxor(Word(Out,i-1),Word(Out,i-4))];   
end;
end
end

