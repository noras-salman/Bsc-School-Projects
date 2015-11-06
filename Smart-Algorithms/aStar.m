clc;clear
cm=[0 10 5 0 20 0 10;...
    0 0 15 20 5 0 0;...
    0 0 0 20 5 0 0;...
    0 0 0 0 0 10 0;...
    0 0 0 0 0 10 5;...
    0 0 0 0 0 0 30;...
    0 0 0 0 0 0 0];
ids=['A','B','C','D','E','F','G'];
h=[15 10 12 8 7 0 14];

START='A';
END='F';
%%=============== End Of Input==================

target=START;
path=[START];
pathLength=0;
f=[];
%% ====== Start the Search ============================
while (target~=END)
i=find(ids==target);
branches=[find(cm(:,i)~=0)' find(cm(i,:)~=0)];
gx=pathLength+cm(branches,i)'+cm(i,branches);
hx=h(branches);
fx=gx+hx;
%% ==== Put in a matrix and chose the best F(X)
f=[f;gx' hx' fx' branches']
minF=min(f(:,3));
branchesMinF=find(f(:,3)==minF)    %% find the index in f(x)

if (length(branchesMinF)>1)   %% if more than one node have the same F(x) Chose The Latest One
    branchesMinF=max(branchesMinF);
end;
fIndex=f(branchesMinF,4);      %% find the Min index in branches
pathLength=f(branchesMinF,1);  %% add the length of chosen path
%% ====== Delete Chosen fx from next posibeliti because allredy taken
f(branchesMinF,:)=[];

%% ====== Set Chosen Node as a new Target ============================
target=ids(fIndex);

%% ====== Add That Node to The Path ============================
path=[path target];
end;
fprintf('Done! End Node Reached By Path\n');
path
pathLength
bg=biograph(cm,ids,'LayoutType','equilibrium','ArrowSize',0,'ShowWeights','on');
g=view(bg);