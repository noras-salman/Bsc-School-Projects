function [ Out ] = Rcon( RoundNum )
%RCON 

rC=['01020408102040801b36';...
    '00000000000000000000';...
    '00000000000000000000';...
    '00000000000000000000'];
Out=hex2dec([rC(:,2*RoundNum-1) rC(:,2*RoundNum)]);

end

