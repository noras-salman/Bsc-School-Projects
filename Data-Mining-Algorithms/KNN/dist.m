function [ value ] = dist( ClassIn1,ClassIn2 )

x=0;
for i=1:length(ClassIn1)
  x=x+ (ClassIn1(i)-ClassIn2(i))^2;
end;
value=sqrt(x);
end

