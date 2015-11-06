clc;
clear;
close all;

%% Search Tree
st=[0 1 1 1 0 0 0 0 0 0 0 0 0 0;...% A=(1,1,1,1)Start
    0 0 0 0 0 0 0 0 0 0 0 0 0 0;...% B=(2,2,1,1)Ignored
    0 0 0 0 1 0 0 0 0 0 0 0 0 0;...% C=(2,1,2,1)
    0 0 0 0 0 0 0 0 0 0 0 0 0 0;...% D=(2,1,1,2)Ignored
    0 0 0 0 0 1 1 0 0 0 0 0 0 0;...% E=(1,1,2,1)
    0 0 0 0 0 0 0 1 1 0 0 0 0 0;...% F=(2,2,2,1)
    0 0 0 0 0 0 0 0 0 1 1 0 0 0;...% G=(2,1,2,2)
    0 0 0 0 0 0 0 0 0 0 0 0 0 0;...% H=(1,2,2,1)Ignored
    0 0 0 0 0 0 0 0 0 0 0 1 0 0;...% I=(1,2,1,1)
    0 0 0 0 0 0 0 0 0 0 0 0 0 0;...% J=(1,1,2,2)Ignored
    0 0 0 0 0 0 0 0 0 0 0 1 0 0;...% K=(1,1,1,2)
    0 0 0 0 0 0 0 0 0 0 0 0 1 0;...% L=(2,2,1,2)
    0 0 0 0 0 0 0 0 0 0 0 0 0 1;...% M=(1,2,1,2)
    0 0 0 0 0 0 0 0 0 0 0 0 0 0];  % N=(2,2,2,2)End
   %A B C D E F G H I J K L M  N

fprintf('The re-Presentation Of Problem Sheep-Wolf-Grass is a data set of 4 elements \n');
fprintf('(The Farmer ,The Wolf ,The Sheep ,The Grass) each of the elemsnts takes the \n');
fprintf('value (1 | 2) 1 means that the elment is on the first side of the river and \n');
fprintf(' 2 means that the elment is on the second side of the river. \n');
fprintf('that mean that we start with the initial value for the set (1,1,1,1) and the \n');
fprintf('the goal is to get a set value with (2,2,2,2) .\n \n \n');


x=[0:15];
AllStates=dec2bin(x);
for i=1:size(AllStates,1)
    for j=1:size(AllStates,2)
        if AllStates(i,j)=='0'
AllStates(i,j)='1';
        else
AllStates(i,j)='2';
        end;
    end;
end;
AllStates
fprintf('(The Farmer ,The Wolf ,The Sheep ,The Grass)\n');
ids=['A':'N'];
START='A';
END='N';
h=ones(1,14);
h(2)=100;
h(4)=100;
h(8)=100;
h(10)=100;
h(14)=0;
target=START;
path=[START];
pathLength=0;
f=[];

fprintf('\n \nApplying A* Algorithm ... \n \n');
%% ====== Start the Search ============================
while (target~=END)
i=find(ids==target);
branches=[find(st(i,:)~=0)];
gx=pathLength+st(branches,i)'+st(i,branches);

hx=h(branches);
fx=gx+hx;
%% ==== Put in a matrix and chose the best F(X)
fprintf('g(x)  h(x)  f(x)  branch');
f=[f;gx' hx' fx' branches']
minF=min(f(:,3));
minF
branchesMinF=find(f(:,3)==minF)    %% find the index in f(x)

if (length(branchesMinF)>1)   %% if more than one node have the same F(x) Chose The Latest One
    if (f(branchesMinF(1),4)==f(branchesMinF(end),4) )
     f(branchesMinF(1),:)=[]; 
     branchesMinF=find(f(:,3)==minF)
    end;
   if (f(branchesMinF(1),1)==f(branchesMinF(end),1) )
     f(branchesMinF(1),:)=[]; 
     branchesMinF=find(f(:,3)==minF)
    end;
        branchesMinF=max(branchesMinF);

end;
fIndex=f(branchesMinF,4);      %% find the Min index in branches
pathLength=f(branchesMinF,1);  %% add the length of chosen path
%% ====== Delete Chosen fx from next posibeliti because allredy taken
f(branchesMinF,:)=[];

%% ====== Set Chosen Node as a new Target ============================
target=ids(fIndex)

%% ====== Add That Node to The Path ============================
path=[path target];
end;
fprintf('Done! End Node Reached By Path\n');
path
pathLength

ids={'A=(1,1,1,1)','B=(2,2,1,1)','C=(2,1,2,1)','D=(2,1,1,2)','E=(1,1,2,1)'...
    ,'F=(2,2,2,1)','G=(2,1,2,2)','H=(1,2,2,1)','I=(1,2,1,1)','J=(1,1,2,2)'...
    ,'K=(1,1,1,2)','L=(2,2,1,2)','M=(1,2,1,2)','N=(2,2,2,2)'};
bg=biograph(st,ids,'LayoutType','hierarchical','ArrowSize',2,'ShowWeights','on');
g=view(bg);
%bg=biograph(sg,ids,'LayoutType','equilibrium','ArrowSize',8,'ShowWeights','on');
%g=view(bg);
