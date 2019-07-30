function [ wordOut ] = wordSubByte( In )
%WORDSUBBYTE Summary of this function goes here
%   Detailed explanation goes here
wordOut=zeros(4,1);
for i=1:4
    wordOut(i,1)=invSubByte(In(i,1));
end;

end

