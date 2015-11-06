function [ pickedCell, Nextboard,GameStatus ] = PlayX( board )
%PLAYX [ pickedCell ,Nextboard ,GameStatus] = PlayX( CurrentbordState )
% GameStatus=0 is finished 1 if sill on
% The function input is the current board state
% The function will chose the best move
% The function will pick a cell in range of 1-9
%   | 1 4 7 |
%   | 2 5 8 |
%   | 3 6 9 |
%  This code is a home work for the Machine Learning Class
% Done By Noras Salman
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
        e2=max(e1);% Just for testing somthing dont forget to delet it!
        for c=1:length(e1)
         switch e1(c) 
             case 3  
                 e1(c)= 7^7;
             case -1  
                 e1(c)= 6^6;
             case 2.5 
                 e1(c)= 5^5;
             case 2   
                 e1(c)= 4^4;
             case 1  
                 e1(c)= 3^3;
             case 0.5 
                 e1(c)= 2^2;
         end;
        end;
        e(i,j)=sum(e1); %% Calculating Priorities
        
        else
        e(i,j)=0;    
        end;
        board=temp;
    end;
end;
board=temp;

e;% the evaluation matrix
x=reshape(e,1,9);
realIndex=[1:9];

searchIn=UnChosenCells';

[m,i]=max(x(searchIn));
ii=find(realIndex==searchIn(i));

board=reshape(board,1,9);
board(ii)=1;
Nextboard=reshape(board,3,3);
pickedCell=ii;
GameStatus = Check_ifEnded( Nextboard );
end

