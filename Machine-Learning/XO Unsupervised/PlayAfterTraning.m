clc;
X=[];
y=[];

board(1:3,1:3)=0.5
avalablePickups=find(board==0.5)
GameStatus=1;
while ((~isempty(avalablePickups))&& (GameStatus==1))

clc;
board=reshape(board,3,3)
avalablePickups=find(board==0.5);

if (~isempty(avalablePickups))
board=reshape(board,1,9);
temp=board;
MachinePick=[];
for i=1:length(avalablePickups)
board(avalablePickups(i))=1;
MachinePick(i)=predict(Theta1, Theta2,getFeaturesFromBoard(reshape(board,3,3)));
board=temp;
end;
getFeaturesFromBoard(reshape(board,3,3))
MachinePick;
[k i]=max(MachinePick);
board(avalablePickups(i))=1;
board=reshape(board,3,3);
GameStatus = Check_ifEnded( board );
end;
avalablePickups=find(board==0.5);
board

if ((~isempty(avalablePickups)) && (GameStatus==1))
avalablePickups'
myPick=input('pick up a place (O):');
while (isempty(find(avalablePickups==myPick))||(isempty(myPick)))
fprintf('Please Select a Choise from avalable Pick Ups\n');
myPick=input('pick up a place (O):');
end;
board=reshape(board,1,9);
board(myPick)=-1;
end;
end;
if (isempty(avalablePickups) && (GameStatus==1))
    fprintf('Good Game!...Its A Tie \n');
end;

