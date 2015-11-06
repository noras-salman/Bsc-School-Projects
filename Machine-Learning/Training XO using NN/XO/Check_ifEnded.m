function [ GameStatus ] = Check_ifEnded( board )
% gameStatus =0 the game has ended
% gameStatue =1 the game is still on
% To win we have to check if there is three X's in a row 
% ie three Ones in a row (row ,column,diagonal)


for i=1:3
    for j=1:3 
        e1=[sum(board(i,:)) sum(board(:,j))];
        if i==j
        e1=[e1 sum(diag(board))];
        end;
        if (((i==1)&&(j==3))||((i==3)&&(j==1))||((i==2)&&(j==2)))
           invdiag=sum([board(1,3) board(2,2) board(3,1)]);
           e1=[e1 invdiag]; 
        end;
        e(i,j)=max(e1); %% Calculating Prioritie
    end;
end;
if max(e)==3 %there's three Ones in a row
GameStatus=0;
fprintf('I WON!! HA HA !\n');
else
GameStatus=1;
end;

end

