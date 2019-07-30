function [ wordOut ] = invMixColumn( wordIn )
%inputed words is must be dec

mC=[14 11 13 09;...
    09 14 11 13;...
    13 09 14 11;...
    11 13 09 14];

wordOut=zeros(4,1);

for i=1:4
    b=[];
    for j=1:4
        if mC(i,j)==9 
            b(j)= mul9mix(wordIn(j,1));
        elseif mC(i,j)==11
            b(j)= mul11mix(wordIn(j,1));
        elseif mC(i,j)==13
            b(j)= mul13mix(wordIn(j,1));
            elseif mC(i,j)==14
            b(j)= mul14mix(wordIn(j,1));
        end; 
    end;
    wordOut(i,1)=bitxor(bitxor(b(1),b(2)),bitxor(b(3),b(4)));
end;
end

