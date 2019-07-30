function [ BlockOut ] = invShiftRows( BlockIn )

BlockOut(1,:)=BlockIn(1,:);
 % d5 ef ca 9f -> 9f d5 df ca
temp=BlockIn(2,1:3); 
temp2=BlockIn(2,4); 
BlockOut(2,1)=temp2; 
BlockOut(2,2:4)=temp;


temp=BlockIn(3,1:2);
temp2=BlockIn(3,3:4);
BlockOut(3,1:2)=temp2;
BlockOut(3,3:4)=temp;

temp=BlockIn(4,1);
temp2=BlockIn(4,2:4);
BlockOut(4,1:3)=temp2;
BlockOut(4,4)=temp;


end

