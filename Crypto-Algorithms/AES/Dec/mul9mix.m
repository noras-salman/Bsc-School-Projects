function [ OutByte ] = mul9mix( InByte )

x=mul2mix(InByte);
x=mul2mix(x);
x=mul2mix(x);
x=bitxor(x,InByte);
OutByte=x;


end