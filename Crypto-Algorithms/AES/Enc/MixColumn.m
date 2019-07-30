function [ wordOut ] = MixColumn( wordIn )
%inputed words is must be dec

mC=[02 03 01 01;...
    01 02 03 01;...
    01 01 02 03;...
    03 01 01 02];
wordOut=zeros(4,1);
norm=hex2dec('1b');
for i=1:4
    b=[];
    for j=1:4
        if mC(i,j)==2 
            b(j)= mul2mix(wordIn(j,1));
        elseif mC(i,j)==3
            b(j)= mul3mix(wordIn(j,1));
        elseif mC(i,j)==1
            b(j)= wordIn(j,1);
        end; 
    end;
    wordOut(i,1)=bitxor(bitxor(b(1),b(2)),bitxor(b(3),b(4)));
end;
end

