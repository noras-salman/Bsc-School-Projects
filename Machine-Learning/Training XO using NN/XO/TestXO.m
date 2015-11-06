clc;
clear;
GamesToPlay=5000;
X=[];
y=[];

board(1:3,1:3)=0.5
avalablePickups=find(board==0.5);
GameStatus=1;
while ((~isempty(avalablePickups))&& (GameStatus==1))
myPick=input('pick up a place (O):');
avalablePickups=find(board==0.5);
pickO=round(rand*9);
while (isempty(find(avalablePickups==myPick)))
    myPick=input('pick up a place (O):');

end;

board=reshape(board,1,9);
board(myPick)=-1;
%board(pickO)=-1;
board=reshape(board,3,3)
avalablePickups=find(board==0.5);
if (~isempty(avalablePickups))
 X(end+1,:)=reshape(board,1,9);
[p,board,GameStatus]=PlayX(board);
y(end+1)=p;
end;
board
end;
if (isempty(avalablePickups) && (GameStatus==1))
    fprintf('Good Game!...Its A Tie \n');
end;

y=y';

save('traningdata.mat','X','y');
