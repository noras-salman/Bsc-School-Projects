function [ pickedCell, Nextboard,GameStatus ] = PlayX( board )
%PLAYX [ pickedCell ,Nextboard ,GameStatus] = PlayX( CurrentbordState )
% GameStatus=0 is finished 1 if sill on
% The function input is the current board state
% The function will chose the best move
% The function will pick a cell in range of 1-9
%   | 1 4 7 |
%   | 2 5 8 |
%   | 3 6 9 |
GameStatus=1;
UnChosenCells=find(board==0.5);
ChosenCells=find(board~=0.5);
temp=board;
for i=1:3
    for j=1:3 
        board=temp;
        if board(i,j)==0.5
        board(i,j)=1;    
        e1=[sum(board(i,:)) sum(board(:,j))];
        if i==j
            e1=[e1 sum(diag(board))];
        end;
        if (((i==1)&&(j==3))||((i==3)&&(j==1))||((i==2)&&(j==2)))
           invdiag=sum([board(1,3) board(2,2) board(3,1)]);
           e1=[e1 invdiag]; 
        end;
        e(i,j)=max(e1);%winning evaluation
        e2(i,j)=min(e1);%losing evaluation
        else
        e(i,j)=0;
        e2(i,j)=0;    
        end;
        board=temp;
    end;
end;
board=temp;
if ~isempty(find(e2==-1)) %losing problem
x=reshape(e2,1,9);
i=find(x==-1);
board=reshape(board,1,9);
board(i)=1;
Nextboard=reshape(board,3,3);
pickedCell=i;

fprintf('I wont lose!! HA HA !\n');
elseif ~isempty(find(e==3))%winning
x=reshape(e,1,9);
i=find(x==3);
board=reshape(board,1,9);
board(i)=1;
Nextboard=reshape(board,3,3);
pickedCell=i;
GameStatus=0;
fprintf('I WON!! HA HA !\n'); 
else % no losing problem nor winning check for best spot

%% Calculating Priorities
x=reshape(e,1,9);
x2=reshape(e2,1,9);
realIndex=[1:9];
y=[];
indexing=[];
searchIn=UnChosenCells';
for i=1:length(UnChosenCells)
if i==2
[m,ii]=min(x2(searchIn));
else
[m,ii]=max(x(searchIn));
end;
y=[y m];
indexing=[indexing find(realIndex==searchIn(ii))];
searchIn(ii)=[];
end;

priorites=y;


%% Calculating priorities Evaluation
for i=length(priorites):-1:1
   indexingP(i)=(i+1)^(i+1); 
end;
indexingP(1:end)=indexingP(end:-1:1);
%indexing
indexingP(UnChosenCells)=indexingP;
indexingP(ChosenCells)=0;
%reshape(indexingP,3,3)

%% Find the best Pick and return it
[y,i]=max(indexingP);
board=reshape(board,1,9);
board(i)=1;
Nextboard=reshape(board,3,3);
pickedCell=i;
end;
end

