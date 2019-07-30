function [ OutByte ] = mul2mix( InByte )

const=hex2dec('1b');
x=dec2bin(InByte);
if length(x)<8
w=8-length(x);
pad=[];
for i=1:w
    pad=['0' pad];
end;
x=[pad x];
end;
if x(1)=='0'
    y=[x(2:end) '0'];
    y=bin2dec(y);
else
    y=[x(2:end) '0'];
    y=bin2dec(y);   
    y=bitxor(y,const);
end;
    OutByte=y;
end

