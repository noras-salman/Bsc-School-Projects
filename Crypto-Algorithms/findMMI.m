function [ keyOut ] = findMMI( keyIn )
%A function to find MMI(Multiplicated Moudle Inverse)
%Usage :
%        [ keyOut ] = findMMI( keyIN )
%MMI= | 1  |   3  |   5  |   7  |   9 |   11 |   15 |   16 |   21 |   23  |  25
%     | 1  |   9  |  21  |  15  |   3 |   19 |   17 |   11 |    5 |   17  |  25
%
% By (Noras Salman) 2012 Tishreen University
if keyIn<0
    keyIn=mod(keyIn,26);
end
MMI=[1 3 5 7 9 11 15 16 21 23 25;...
     1 9 21 15 3 19 17 11 5 17 25];

 indx=find(MMI(1,:)==keyIn);
 keyOut=MMI(2,indx);
end

