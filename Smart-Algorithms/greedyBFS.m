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
%% =============== End Of Input==================

target=START;
path=[START];
pathLength=0;
%% ====Start The Search =========================
while (target~=END)
i=find(ids==target);
branches=[find(cm(:,i)~=0)' find(cm(i,:)~=0)];
branchesH=h(branches);
minH=min(branchesH);
branchesMinH=find(h==minH);%% find the index in h(x)
pathLength=pathLength+cm(i,branchesMinH); %%add the length of chosen path

%% ====== Print info ============================
fprintf(target);
fprintf(' ===>>> ');
fprintf(ids(branches));
fprintf(' minimum value is ');
fprintf('%6.0f',minH);

%% ======= set new Chosen Node As A target=====
target=ids(branchesMinH);

%% ====== Print info ============================
fprintf(' Chosen Node is ( ');
fprintf(target);
fprintf(' )');
fprintf('\n');

%% ====== Add to Path ============================
path=[path target];

end;
fprintf('Done! End Node Reached By Path\n');
path
pathLength
%% ====== Draw The Graph =========================
bg=biograph(cm,ids,'LayoutType','equilibrium','ArrowSize',0,'ShowWeights','on');
g=view(bg);