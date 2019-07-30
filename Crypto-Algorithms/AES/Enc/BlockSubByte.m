function [ out ] = BlockSubByte( in )

for i=1:4
    out(:,i)=wordSubByte(in(:,i));
end;

end

