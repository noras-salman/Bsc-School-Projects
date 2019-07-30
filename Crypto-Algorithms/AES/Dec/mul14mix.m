function [ OutByte ] = mul14mix( InByte )


x=mul2mix(InByte);
x=bitxor(x,InByte);
x=mul2mix(x);
x=bitxor(x,InByte);
x=mul2mix(x);
OutByte=x;

end

