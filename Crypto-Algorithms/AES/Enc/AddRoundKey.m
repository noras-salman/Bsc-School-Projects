function [ out ] = AddRoundKey( block ,key )

out=bitxor(block,key);
end

