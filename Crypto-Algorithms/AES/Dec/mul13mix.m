function [ OutByte ] = mul13mix( InByte )

x=mul2mix(InByte);
x=bitxor(x,InByte);
x=mul2mix(x);
x=mul2mix(x);
x=bitxor(x,InByte);
OutByte=x;


end

