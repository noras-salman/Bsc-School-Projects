clc;clear

%% Start State
board(1:3,1:3)=0.5;
GameStatus=1;
avalablePicks=find(board==0.5);
depth=1;
%% Begin The Game
while ((~isempty(avalablePicks))&& (GameStatus==1))


avalablePicks=find(board==0.5);

%% Computer Playing X

[ pickedCell, board,GameStatus,evaluation ] = PlayX( board );
h=1./evaluation;
g=ones(3)*depth;
f=h+depth;
Hx=num2str(h)
Gx=num2str(g)
Fx=num2str(f)
depth=depth+1;
%boardState=['H=' num2str(h) ' G(x)=' num2str(g) ' F(x)=' num2str(f)];
%fprintf('%s \n',boardState);
board(pickedCell)=1

avalablePicks=find(board==0.5);
%% User Playing O
if ((isempty(avalablePicks))|| (GameStatus==0))
break;
end;
avalablePicks'
myPick=input('pick up a place (O):');
while (isempty(find(avalablePicks==myPick))||(isempty(myPick)))
fprintf('Please Select a Choise from avalable Pick Ups\n');
myPick=input('pick up a place (O):');
end;
%clc;
board(myPick)=-1


end;
