function [ OutByte ] = mul11mix( InByte )


x=mul2mix(InByte);
x=mul2mix(x);

x=bitxor(x,InByte);

x=mul2mix(x);
x=bitxor(x,InByte);
OutByte=x;


end

