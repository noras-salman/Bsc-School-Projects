function [ OutByte ] = mul3mix( InByte )

y=mul2mix(InByte);
y=bitxor(y,InByte);
OutByte=y;

end

