clc;
clear;
X=[];
y=[];
load('hypothisis.mat');
board(1:3,1:3)=0.5
avalablePickups=find(board==0.5);
GameStatus=1;
while ((~isempty(avalablePickups))&& (GameStatus==1))

avalablePickups'
myPick=input('pick up a place (O):');
while (isempty(find(avalablePickups==myPick))||(isempty(myPick)))
fprintf('Please Select a Choise from avalable Pick Ups\n');
myPick=input('pick up a place (O):');
end;
clc;
board=reshape(board,1,9);
board(myPick)=-1;

board=reshape(board,3,3)
avalablePickups=find(board==0.5);
avalablePickups'
if (~isempty(avalablePickups))
board=reshape(board,1,9);
MachinePick=predict(Theta1, Theta2, board)
board(MachinePick)=1;
board=reshape(board,3,3)
GameStatus = Check_ifEnded( board );
end;
avalablePickups=find(board==0.5);
board
end;
if (isempty(avalablePickups) && (GameStatus==1))
    fprintf('Good Game!...Its A Tie \n');
end;

