Min_Supp=2;

%% Load DataSet
DataSet

%% join step vs. prune step
n=size(D,1);
T=D(:,2);
L=[];
C=[0];
%intersect(a,b)

itemCount=1;
while ~isempty(C)
  K0=[];
  L=[];
 for i=1:n
    T0=cell2mat(T(i));
    for j=1:(length(T0)-itemCount+1)
         if length(T0)~=itemCount
        for k=0:itemCount-1
            
            K0=[K0 T0(j+k)];
        end;
        K0;
        L=[L ;K0];
        K0=[];
         else
         L=[L ;T0]  ;
         end;
    end;
end;
L%=unique(L,'rows')
C=[];
for i=1:n
    for j=1:size(L,1)
     if (sum(ismember(cell2mat(T(i)),L(j,:)))==itemCount)
     C=[C ;L(j,:)];
    end;
    end;
end;
C=unique(C,'rows')
itemCount=itemCount+1;

end;


















